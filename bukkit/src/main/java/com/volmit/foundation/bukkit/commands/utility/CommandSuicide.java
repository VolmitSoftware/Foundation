package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("suicide")
@Alias({"unstuck", "kms"})
@Permission("foundation.util.suicide")
public class CommandSuicide implements FCommand {
    @Default
    public static void suicide(CommandSender sender) {
        if (sender instanceof Player p) {
            p.setHealth(0);
            FConst.success("Player has died by their own choice(" + p.getName() + ")").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
