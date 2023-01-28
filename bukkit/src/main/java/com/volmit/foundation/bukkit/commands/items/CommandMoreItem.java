package com.volmit.foundation.bukkit.commands.items;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command("more")
@Alias({"refill", "fill", "maxstack", "maxstacksize"})
@Permission("foundation.items.more")
public class CommandMoreItem implements FCommand {
    @Default
    @Permission("foundation.items.more")
    public static void moreItem(CommandSender sender) {
        if (sender instanceof Player p) {
            ItemStack itemInHand = p.getInventory().getItemInMainHand();
            if (itemInHand.getType() != Material.AIR) {
                ItemStack maxStack = new ItemStack(itemInHand.getType(), itemInHand.getMaxStackSize());
                p.getInventory().setItemInMainHand(maxStack);
                FConst.success("Refilled your hand to the maximum stack size!").send(sender);
            } else {
                FConst.error("Your hand is empty, please hold an item.").send(sender);
            }
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
