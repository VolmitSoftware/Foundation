package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command("repair")
@Permission("foundation.util.repair")
public class CommandRepair implements FCommand {
    @Default
    @Permission("foundation.util.repair")
    public static void repair(CommandSender sender) {
        if (sender instanceof Player p) {
            ItemStack item = p.getInventory().getItemInMainHand();
            item.setDurability((short) 0);
            p.getInventory().setItemInMainHand(item);
            FConst.success("You have repaired your item").send(p);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
