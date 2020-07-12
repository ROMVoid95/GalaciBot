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
package net.romvoid.gcbot.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Setup {

    private static final BufferedReader sys_in;

    static {
        InputStreamReader isr = new InputStreamReader(System.in);
        sys_in = new BufferedReader(isr);
    }

    public static String prompt(String req) {
        String token;

        // prompt for token
        System.out.println("Enter The Bots " + req + ":");


        try {
            // read and trim line
            String line = sys_in.readLine();
            token = line.trim();

            return token;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        // RAGEQUIT
        System.out.println("Exiting");
        Runtime.getRuntime().exit(1);
        return null;
    }
}
