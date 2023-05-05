/*------------------------------------------------------------------------------
 -   BukkitFoundation is a Skill/Integration plugin  for Minecraft Bukkit Servers
 -   Copyright (c) 2022 Arcane Arts (Volmit Software)
 -
 -   This program is free software: you can redistribute it and/or modify
 -   it under the terms of the GNU General Public License as published by
 -   the Free Software Foundation, either version 3 of the License, or
 -   (at your option) any later version.
 -
 -   This program is distributed in the hope that it will be useful,
 -   but WITHOUT ANY WARRANTY; without even the implied warranty of
 -   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 -   GNU General Public License for more details.
 -
 -   You should have received a copy of the GNU General Public License
 -   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 -----------------------------------------------------------------------------*/
package com.volmit.foundation.bukkit;

import art.arcane.amulet.io.IO;
import com.google.gson.Gson;
import com.theokanning.openai.service.OpenAiService;
import com.volmit.foundation.bukkit.util.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("ALL")
@Getter
public class BukkitFoundationConfig {
    private static BukkitFoundationConfig config = null;
    public boolean debug = false;
    public boolean autoUpdateCheck = true;
    public boolean useSplashScreen = true;
    public String gptTokenApi = "your_token";
    public String gptModel = "gpt-3.5-turbo";
    public long gptMaxDurationSeconds = 25;
    public int gptMaxTokensPerResponse = 100;

    @Setter
    private boolean verbose = false;

    public static BukkitFoundationConfig get() {
        if (config == null) {
            BukkitFoundationConfig dummy = new BukkitFoundationConfig();
            File bukkitConfig =  BukkitFoundation.instance.getDataFile("foundation", "Foundation.json");


            if (!bukkitConfig.exists()) {
                try {
                    IO.writeAll(bukkitConfig, new JSONObject(new Gson().toJson(dummy)).toString(4));
                } catch (IOException e) {
                    e.printStackTrace();
                    config = dummy;
                    return dummy;
                }
            }

            try {
                config = new Gson().fromJson(IO.readAll(bukkitConfig), BukkitFoundationConfig.class);
                IO.writeAll(bukkitConfig, new JSONObject(new Gson().toJson(config)).toString(4));
            } catch (IOException e) {
                e.printStackTrace();
                config = new BukkitFoundationConfig();
            }
        }

        return config;
    }
}
