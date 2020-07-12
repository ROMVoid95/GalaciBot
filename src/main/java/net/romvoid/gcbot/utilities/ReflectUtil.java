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

import java.util.Set;

import org.reflections.Reflections;

import net.romvoid.gcbot.commands.inerf.Command;

public class ReflectUtil {

	public static Set<Class<? extends Command>> getCommandClasses() {
		Reflections reflections = new Reflections("net.romvoid.gcbot.commands");
        Set<Class<? extends Command>> scannersSet = reflections.getSubTypesOf(Command.class);
		return scannersSet;
	}
}
