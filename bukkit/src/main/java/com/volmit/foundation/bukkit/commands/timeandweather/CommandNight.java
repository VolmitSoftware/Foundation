package com.volmit.foundation.bukkit.commands.timeandweather;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("night")
@Permission("foundation.util.night")
public class CommandNight implements FCommand {
    @Default
    @Permission("foundation.util.night")
    public static void night(CommandSender sender) {
        if (sender instanceof Player p) {
            p.getWorld().setTime(13000);
            FConst.success("Set time to night").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
