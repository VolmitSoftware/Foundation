package com.volmit.foundation.bukkit.commands.positionals;

import com.volmit.foundation.bukkit.impl.BukkitPlayer;
import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.entity.Player;

@Command("back")
@Alias({"bak"})
@Permission("foundation.back")
public class CommandBack implements FCommand {
    @Default
    public static void back(Player player) {
        if (new BukkitPlayer(player).goBack()) {
            FConst.success("Teleported to your previous position").send(player);
        } else {
            FConst.error("You have gone back as far as you can!").send(player);
        }
    }
}
