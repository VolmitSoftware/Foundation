package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("kickall")
@Permission("foundation.util.kickall")
public class CommandKickall implements FCommand {
    @Default
    @Permission("foundation.util.kickall")
    public static void kickall(CommandSender sender) {
        if (sender instanceof Player p) {
            for (Player player : p.getServer().getOnlinePlayers()) {
                player.kickPlayer("You have been kicked by " + p.getName());
            }
            FConst.success("Kicked all players").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
