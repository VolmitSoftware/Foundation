package com.volmit.foundation.bukkit.commands.time;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("time")
@Permission("foundation.util.time")
public class CommandTime implements FCommand {
    @Default
    @Permission("foundation.util.time")
    public static void time(CommandSender sender, @AIntegerArgument int time) {
        if (sender instanceof Player p) {
            p.getWorld().setTime(time);
            FConst.success("Set time to " + time).send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}