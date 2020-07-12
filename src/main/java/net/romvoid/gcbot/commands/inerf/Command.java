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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * The Class Command.
 */
public abstract class Command {

    /** The command name. */
    protected String name;
    
    /** The commands parameters. */
    protected List<String> parameters;
    
    /** The commands aliases. */
    protected Set<String> aliases;

    /**
     * Instantiates a new command.
     *
     * @param name the name
     */
    public Command(String name) {
        this.name = name;
        this.parameters = new ArrayList<>(3);
        this.aliases = new HashSet<>();
    }

    /**
     * Adds the alias.
     *
     * @param alias the alias
     * @return the command
     */
    public Command addAlias(String alias) {
        aliases.add(alias);
        return this;
    }

    /**
     * @param args         arguments for the command
     * @param channel      channel where the command is executed
     * @param author       who invoked the command
     * @param inputMessage the incoming message object
     * @return the message to output or an empty string for nothing
     */
    public abstract void executeAndHandle(GuildMessageReceivedEvent event, List<String> params, User author, Message inputMessage);
    

    /**
     * Gets the name.
     *
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public abstract String getDescription();

    /**
     * Gets the parameters.
     *
     * @return {@link #parameters}
     */
    public List<String> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    /**
     * Gets the aliases.
     *
     * @return {@link #aliases}
     */
    public Set<String> getAliases() {
        return Collections.unmodifiableSet(aliases);
    }
}
