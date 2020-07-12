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
package net.romvoid.gcbot.commands.inerf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.romvoid.gcbot.utilities.ReflectUtil;

/**
 * The Class CommandManager.
 */
public class CommandManager {

	/** The commands. */
	private Map<String, Command> commands;

	/**
	 * Instantiates a new command manager.
	 */
	public CommandManager() {
		commands = new HashMap<>();
		getCmds();
	}

	/**
	 * Handle command.
	 *
	 * @param commandName the command name
	 * @param event       the event
	 */
	public void handleCommand(String commandName, GuildMessageReceivedEvent event) {
		Optional<Command> commandOptional = commandFromName(commandName);

		// Adds any space separated strings to the parameter list
		commandOptional.ifPresent(command -> {
			String[] tokens = event.getMessage().getContentRaw().substring(1).toLowerCase().split(" ", 2);
			List<String> paramList = new ArrayList<>();
			if (hasParams(tokens)) {
				final String params = tokens[1].trim();
				paramList = new ArrayList<>(Arrays.asList(params.split(" ")));
			}
			command.executeAndHandle(event, paramList,null, null);
		});
	}

	private void getCmds() {
		for (Class<? extends Command> clazz : ReflectUtil.getCommandClasses()) {
			register(clazz);
		}
	}

	/**
	 * Register.
	 *
	 * @param clazz the command
	 */
	public void register(Class<? extends Command> clazz) {
		Command command;
		try {
			command = clazz.newInstance();

			commands.put(command.getName().toLowerCase(), command);

			for (String alias : command.getAliases())
				commands.put(alias.toLowerCase(), command);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Checks for params.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	private boolean hasParams(String[] tokens) {
		return tokens.length > 1;
	}

	/**
	 * Command from name.
	 *
	 * @param name the name
	 * @return the optional
	 */
	public Optional<Command> commandFromName(String name) {
		return Optional.ofNullable(commands.get(name));
	}
}
