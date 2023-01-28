package com.volmit.foundation.bukkit.commands.items;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AItemStackArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command("i")
@Alias({"item", "itm", "additem"})
@Permission("foundation.items.give")
public class CommandGiveItem implements FCommand {
    @Default
    @Permission("foundation.items.give")
    public static void giveItem(CommandSender sender, @AItemStackArgument ItemStack item, @AIntegerArgument(min = 1, max = 64) int amount) {
        if (sender instanceof Player p) {
            ItemStack itemStack = new ItemStack(item.getType());
            itemStack.setAmount(amount);
            p.getInventory().addItem(itemStack);
            FConst.success("Gave you " + item.getType().getKey()).send(sender);
        } else {
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }

    @Default
    @Permission("foundation.items.give")
    public static void giveItem(CommandSender sender, @AItemStackArgument ItemStack item) {
        if (sender instanceof Player p) {
            ItemStack itemStack = new ItemStack(item.getType());
            p.getInventory().addItem(itemStack);
            FConst.success("Gave you " + item.getType().getKey()).send(sender);
        } else {
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
