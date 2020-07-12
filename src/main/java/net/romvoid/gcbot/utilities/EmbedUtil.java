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
package net.romvoid.gcbot.utilities;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.romvoid.gcbot.Bot;

/**
 * Generates common embed messages.
 * 
 * @author ROMVoid
 */
public class EmbedUtil {
    /**
     * The default deletion interval.
     * TODO: Create a config-option to set this per-guild
     */
    private static final long defaultDeleteInterval = 60;
    /**
     * The default deletion interval TimeUnit.
     * TODO: Create a config-option to set this per-guild
     */
    private static final TimeUnit defaultDeleteIntervalTimeUnit = TimeUnit.SECONDS;

    /**
     * Creates an embedded message indicating a successful feature execution.
     *
     * @param title       the title of the embedded.
     * @param description a more detailed description.
     * @return the generated EmbedBuilder.
     */
    public static EmbedBuilder success(String title, String description) {
        return embed(":white_check_mark: " + title, description).setColor(Colors.Universal.PRIMARY);
    }
    
    /**
     * Creates an embedded message with additional information.
     *
     * @param title       the title of the embedded.
     * @param description a more detailed description.
     * @return the generated EmbedBuilder.
     */
    public static EmbedBuilder info(String title, String description) {
        return embed(":information_source: " + title, description).setColor(Colors.Universal.SECONDARY);
    }

    /**
     * Creates an embedded unknown error message.
     *
     * @return the generated EmbedBuilder.
     */
    public static EmbedBuilder error() {
        return error("Unknown error", "An unknown error occurred.");
    }

    /**
     * Creates an embedded error message.
     *
     * @param title       the title of the embedded.
     * @param description the error description.
     * @return the generated EmbedBuilder.
     */
    public static EmbedBuilder error(String title, String description) {
        return embed(":warning: " + title, description).setColor(Colors.Universal.ERROR);
    }

    /**
     * Creates an embedded no permissions message.
     *
     * @return the generated EmbedBuilder.
     */
    public static EmbedBuilder no_permissions() {
        return embed(":warning: " + "No permissions", "You don't have the permission to execute this command!").setColor(Colors.Universal.NO_PERMISSION);
    }


    /**
     * Creates an embedded message.
     *
     * @param title       the title of the embedded.
     * @param description the description (main message).
     * @return the generated EmbedBuilder.
     */
    public static EmbedBuilder embed(String title, String description) {
        return new EmbedBuilder().setTitle(title).setDescription(description);
    }

    /**
     * Attaches a timestamp to an embedded message.
     *
     * @param embedBuilder the EmbedBuilder to attach a timestamp to.
     * @return the given EmbedBuilder with a timestamp.
     */
    public static EmbedBuilder withTimestamp(EmbedBuilder embedBuilder) {
        return embedBuilder.setFooter(Bot.getNewTimestamp(), null);
    }

    /**
     * Converts an EmbedBuilder into a message.
     *
     * @param embedBuilder the EmbedBuilder that contains the embed data.
     * @return the compiled Message.
     */
    public static Message message(EmbedBuilder embedBuilder) {
        return new MessageBuilder().setEmbed(embedBuilder.build()).build();
    }

    /**
     * Always sends a message but deletes it only if the messageChannel is on a server.
     *
     * @param messageChannel the channel to send the message in.
     * @param message        the message to send.
     */
    public static void sendAndDeleteOnGuilds(MessageChannel messageChannel, Message message) {
        sendAndDeleteOnGuilds(messageChannel, message, defaultDeleteInterval, defaultDeleteIntervalTimeUnit);
    }

    /**
     * Always sends a message but deletes it only if the messageChannel is on a server.
     *
     * @param messageChannel         the channel to send the message in.
     * @param message                the message to send.
     * @param deleteInterval         the interval after that the message should be deleted if the channel is on a server.
     * @param deleteIntervalTimeUnit the TimeUnit of deleteInterval.
     */
    public static void sendAndDeleteOnGuilds(MessageChannel messageChannel, Message message, long deleteInterval, TimeUnit deleteIntervalTimeUnit) {
        sendAndDelete(messageChannel, message,
                // only delete message on servers.
                messageChannel.getType() == ChannelType.TEXT ? deleteInterval : -1,
                deleteIntervalTimeUnit);
    }

    /**
     * Sends a message and deletes it after an interval (if interval is >= 0).
     *
     * @param messageChannel         the channel to send the message in.
     * @param message                the message to send.
     * @param deleteInterval         the interval after that the message should be deleted. Message won't be deleted if
     *                               interval is < 0.
     * @param deleteIntervalTimeUnit the TimeUnit of deleteInterval.
     */
    public static void sendAndDelete(MessageChannel messageChannel, Message message, long deleteInterval, TimeUnit deleteIntervalTimeUnit) {
        messageChannel.sendMessage(message).queue(deleteInterval < 0
                // do nothing if interval < 0
                ? null
                // else: delete after interval
                : sentMessage -> sentMessage.delete().queueAfter(deleteInterval, deleteIntervalTimeUnit, null, ignored -> {
            //TODO evaluate exception, i.e. bot does not have permission
        }));
    }
    
	public static void sendSuggestionHelp(MessageChannel channel, String[] tags, String tagUsed) {
		String desc = "please make sure to use a valid tag.";

		StringBuilderHelper string = new StringBuilderHelper();
		for (String s : tags) {
			string.append(" ");
			string.append("  - **" + s + "**");
			string.ln();
		}

		Field tag = new Field("Here are the valid Tags", " \n" + string.toString(), false, true);

		EmbedBuilder embed = new EmbedBuilder().setTitle(tagUsed + " is not a valid tag!").setColor(Colors.Universal.ERROR)
				.setDescription(desc).addField(tag);

		Message mymsg = channel.sendMessage(embed.build()).complete();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				mymsg.delete().queue();
			}
		}, 25000);
	}
	
	public static void sendSuggestionHelp2(MessageChannel channel, String[] tags) {
		String desc = "When making a suggestion, please make sure to use a valid tag. This way readers will know what your suggestion is for.";

		StringBuilderHelper string = new StringBuilderHelper();
		for (String s : tags) {
			string.append(" ");
			string.append("  - **" + s + "**");
			string.ln();
		}

		Field tag = new Field("Here are the valid Tags", string.toString(), false, true);

		EmbedBuilder embed = new EmbedBuilder().setTitle("Whoops! Didn't find a tag!").setColor(Colors.Universal.ERROR)
				.setDescription(desc).addField(tag);

		Message mymsg = channel.sendMessage(embed.build()).complete();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				mymsg.delete().queue();
			}
		}, 25000);
	}
}