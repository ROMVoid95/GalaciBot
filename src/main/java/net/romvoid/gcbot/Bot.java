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

import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MESSAGES;
import static net.dv8tion.jda.api.requests.GatewayIntent.GUILD_PRESENCES;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.JDAImpl;
import net.romvoid.gcbot.commands.inerf.CommandManager;
import net.romvoid.gcbot.config.Configuration;
import net.romvoid.gcbot.config.Setup;

/**
 * The Main Bot Class.
 */
public class Bot {

	private static Bot instance;
	private JDAImpl jda;
	private static final SimpleDateFormat timeStampFormatter = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
	/** The command manager. */
	private CommandManager commandManager;

	/** The Constant CONFIG_KEYS. */
	private static final String[] CONFIG_KEYS = { "token", "prefix" , "suggestionID"};

	/** The configuration. */
	private final Configuration configuration;
	
	private static Set<GatewayIntent> intents = new HashSet<>();
	
	/** The prefix. */
	private static String prefix;


	/**
	 * Instantiates a new bot.
	 */
	private Bot() {
		instance = this;
		configuration = new Configuration(new File("config.json"));
		for (String configKey : CONFIG_KEYS) {
			if (!configuration.has(configKey)) {
				String input = Setup.prompt(configKey);
				configuration.set(configKey, input);
			}
		}
		commandManager = new CommandManager();
		prefix = instance.configuration.getString("prefix");
		setIntents();
		initJDA();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		if (instance != null)
			throw new RuntimeException("CrashBot has already been initialized in this VM.");
		new Bot();
	}

	/**
	 * Initiates the JDA Instance and builder.
	 */
	public static void initJDA() {
		if (instance == null)
			throw new NullPointerException("CrashBot has not been initialized yet.");
		
		try {
        	instance.jda = (JDAImpl) JDABuilder.create(instance.configuration.getString("token"), intents)
			.setStatus(OnlineStatus.DO_NOT_DISTURB)
			.addEventListeners(new MessageListener())
			.disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE)
			.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		getJDA().getPresence().setStatus(OnlineStatus.ONLINE);

	}
	
	private void setIntents() {
		intents.add(GUILD_MESSAGES);
		intents.add(GUILD_MEMBERS);
		intents.add(GUILD_PRESENCES);
	}

	/**
	 * Handle command event.
	 *
	 * @param event the event
	 */
	public void handleCommandEvent(GuildMessageReceivedEvent event) {
		// If the event message is, e.g. !cmd testing testing, commandName is set to
		// "cmd"

			String prefix = getPrefix();
			String commandName = event.getMessage().getContentRaw().substring(prefix.length()).split(" ")[0].toLowerCase();
			commandManager.handleCommand(commandName, event);

	}

	/**
	 * Gets the single instance of Bot.
	 *
	 * @return single instance of Bot
	 */
	public static Bot getInstance() {
		if (instance == null)
			throw new IllegalStateException("Bot has not been initialised. Please use Bot#init() to create the bot");
		return instance;
	}

	/**
	 * Gets the prefix.
	 *
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	public static Configuration getConfiguration() {
		return instance == null ? null : instance.configuration;
	}

	/**
	 * Gets the JDA Instance.
	 *
	 * @return the JDA Instance
	 */
	public static JDAImpl getJDA() {
		return instance == null ? null : instance.jda;
	}
	
	  /**
	   * @return a freshly generated timestamp in the 'dd.MM.yyyy HH:mm:ss' format.
	   */
	  public static String getNewTimestamp() {
	    return timeStampFormatter.format(new Date());
	  }
}
