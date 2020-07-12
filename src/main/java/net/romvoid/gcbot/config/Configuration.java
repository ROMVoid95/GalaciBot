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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Configuration {
    
    private File file;
    private JsonObject json;
    public JsonParser jsonParser;
    

    public Configuration(final File file) {

        this.file = file;
        String cont = null;

        try {
            if (file.exists()) {
            	BufferedReader reader = new BufferedReader(new FileReader(file));
                cont = reader.lines().collect(Collectors.joining("\n"));
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cont == null || cont.equals("")) {
            cont = "{}";
        }
        json = JsonParser.parseString(cont).getAsJsonObject();
        
    }

    /**
     * @param key
     * @param val
     * @description Sets tha value of a key in config
     */
    public Configuration set(final String key, final String val) {
        if (json.has(key)) {
            json.remove(key);
        }
        if (val != null) {
            json.addProperty(key, val);
        }
        return this.save();
    }

    /**
     * @param key
     * @param val
     * @description Sets the value of a key in config
     */
    public Configuration set(final String key, final int val) {
        if (json.has(key)) {
            json.remove(key);
        }
        this.json.addProperty(key, val);
        return this.save();
    }

    /**
     * @param key
     * @description Removes key from config
     */
    public Configuration unset(final String key) {
        if (json.has(key))
            json.remove(key);

        return this.save();
    }

    /**
     * @description Saves the config
     */
    private Configuration save() {
        try {
            if (json.entrySet().size() == 0) {
                if (file.exists()) {
                    file.delete();
                }
            } else {
                if (!file.exists()) {
                    file.createNewFile();
                }

                BufferedWriter br = new BufferedWriter(new FileWriter(file));
                br.write(json.toString());
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * @param key
     * @return Value of key in config as string
     */
    public String getString(final String key) {
        try {
            return json.get(key).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param key
     * @return Value of key in config as integer
     */
    public int getInt(final String key) {
        if (json.has(key)) {
            return json.get(key).getAsInt();
        }
        return 0;
    }

    /**
     * @param key
     * @return If key exists
     */
    public boolean has(final String key) {
        try {
            return json.has(key);
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public List<String> keySet() {
        List<String> keys = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entries = json.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    public List<String> values() {
        List<String> values = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entries = json.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            values.add(entry.getValue().getAsString());
        }
        return values;
    }


}