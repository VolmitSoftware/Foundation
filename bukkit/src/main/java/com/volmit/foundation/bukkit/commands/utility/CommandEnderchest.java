package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("enderchest")
@Alias({"ec", "endchest", "echest"})
@Permission("foundation.util.enderchest")
public class CommandEnderchest implements FCommand {
    @Default
    @Permission("foundation.util.enderchest")
    public static void enderchest(CommandSender sender) {
        if (sender instanceof Player p) {
            p.openInventory(p.getEnderChest());
            FConst.success("Opened your enderchest!").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }

    @Default
    @Permission("foundation.util.enderchest.others")
    public static void enderchest(CommandSender sender, @APlayerArgument Player target) {
        if (sender instanceof Player) {
            target.openInventory(target.getEnderChest());
            FConst.success("Opened " + target.getName() + "'s enderchest!").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
