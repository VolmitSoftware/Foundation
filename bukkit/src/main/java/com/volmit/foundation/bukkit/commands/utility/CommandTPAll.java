package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("tpall")
@Permission("foundation.util.tpall")
public class CommandTPAll implements FCommand {
    @Default
    @Permission("foundation.util.tpall")
    public static void tpall(CommandSender sender) {
        if (sender instanceof Player p) {
            for (Player player : p.getServer().getOnlinePlayers()) {
                player.teleport(p);
            }
            FConst.success("Teleported all players to you").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }

    @Default
    @Permission("foundation.util.tpall.others")
    public static void tpall(CommandSender sender, @APlayerArgument Player target) {
        for (Player player : target.getServer().getOnlinePlayers()) {
            player.teleport(target);
        }
        FConst.success("Teleported all players to " + target.getName() + ".").send(sender);
    }
}