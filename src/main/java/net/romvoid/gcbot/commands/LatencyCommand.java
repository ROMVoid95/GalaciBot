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
package net.romvoid.gcbot.commands;

import java.util.List;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.romvoid.gcbot.Bot;
import net.romvoid.gcbot.commands.inerf.Command;

/**
 * The LatencyCommand.
 */
public class LatencyCommand extends Command {

    /**
     * Instantiates a new latency command.
     */
    public LatencyCommand() {
        super("ping");
        addAlias("latency");
        addAlias("pong");
    }
    

	@Override
	public void executeAndHandle(GuildMessageReceivedEvent event, List<String> params,
			User author, Message inputMessage) {
        event.getChannel().sendMessage("The current latency between me and the discord servers is " +
                Bot.getJDA().getGatewayPing()).queue();
		
	}

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Returns latency between the discord bot and the official discord servers";
    }

}
