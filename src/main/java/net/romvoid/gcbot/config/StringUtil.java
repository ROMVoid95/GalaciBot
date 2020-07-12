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

public class StringUtil {
    /**
     * Check if String is numeric.
     *
     * @param str the String which should be tested.
     * @return true if String is Numeric else false.
     */
    @SuppressWarnings("unused")
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Create Emoji out of Code points
     *
     * @param str the Code point.
     * @return the Emoji as a String.
     */
    public static String emojiOutCodePoint(String str) {
        int[] codepoints = {Integer.valueOf(str.replace("U+", "0x"))};
        String s = new String(codepoints, 0, codepoints.length);
        return s;
    }
    
    public static boolean nicknameHasBrackets(String nickname) {
      if (nickname.contains("[") || nickname.contains("]")) {
        return true;
      }
      return false;
      
    }
}
