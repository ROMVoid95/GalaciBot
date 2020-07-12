/**
 * Copyright (C) 2020 ROMVoid95
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid.gcbot;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.romvoid.gcbot.utilities.EmbedUtil;

/**
 * The listener interface for receiving message events. The class that is
 * interested in processing a message event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addMessageListener<code> method. When the message event
 * occurs, that object's appropriate method is invoked.
 *
 * @see MessageEvent
 */
public class MessageListener extends ListenerAdapter {

	String id = Bot.getConfiguration().getString("suggestionID");
	static String[] tags = { "Discord", "Addon", "Galacticraft", "Rewoven", "discord", "addon", "galacticraft",
			"rewoven" };
	static String[] tagList = { "[Discord]", "[Addon]", "[Galacticraft]", "[Rewoven]" };
	static Pattern pattern = Pattern.compile("(\\[)(\\D{2,})(\\])");

	/**
	 * On guild message received.
	 *
	 * @param event the event
	 */
	@Override
	public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
		if (event.getAuthor().isBot())
			return;

		if (messageContainsPrefix(event)) {
			MessageHistory h = event.getChannel().getHistory();
			List<Message> list;
			try {
				list = h.retrievePast(5).submit().get();
				System.out.println(SECONDS.between(list.get(4).getTimeCreated(), event.getMessage().getTimeCreated()));
				if (SECONDS.between(list.get(4).getTimeCreated(), event.getMessage().getTimeCreated()) <= 15) {
					event.getMessage().delete().complete();
					User u = event.getAuthor();
					event.getChannel().sendMessage(u.getAsMention() + " Slow Down").submit();
					return;
				}
				if (SECONDS.between(list.get(4).getTimeCreated(), event.getMessage().getTimeCreated()) >= 15) {
					Bot.getInstance().handleCommandEvent(event);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		if (event.getChannel().getId().equals(id)) {
			check(event.getMessage(), event.getChannel());
		}

	}

	@Override
	public void onGuildMessageUpdate(@Nonnull GuildMessageUpdateEvent event) {
		System.out.println("message was updated");
		if (event.getChannel().getId().equals(id)) {
			check(event.getMessage(), event.getChannel());
		}
	}

	private static void check(Message message, TextChannel channel) {
		String msg = message.getContentRaw();
		Matcher matcher = pattern.matcher(msg);
		boolean found = matcher.find();
		if (!found) {
			message.delete().queue();
			EmbedUtil.sendSuggestionHelp2(channel, tagList);
			return;
		}
		if (!hasValidTag(matcher.group(2), tags)) {
			message.delete().queue();
			EmbedUtil.sendSuggestionHelp(channel, tagList, matcher.group());
		}
	}

	/**
	 * Returns true if the message contains the bots prefix. TODO: Create a
	 * per-guild prefix option
	 *
	 * @param event the message received event
	 * @return true, if successful
	 */
	private boolean messageContainsPrefix(GuildMessageReceivedEvent event) {
		return event.getMessage().getContentRaw().startsWith(Bot.getInstance().getPrefix());
	}

	private static boolean hasValidTag(String message, String[] list) {
		return Arrays.stream(list).parallel().anyMatch(message::equals);
	}

}
