package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("heal")
@Permission("foundation.heal")
public class CommandHeal implements FCommand {
    @Default
    @Permission("foundation.heal.self")
    public static void heal(Player player) {
        player.setHealth(player.getMaxHealth());
        FConst.success("Healed!").send(player);
    }

    @Default
    @Permission("foundation.heal.others")
    public static void heal(CommandSender sender, @APlayerArgument Player player) {
        player.setHealth(player.getMaxHealth());
        FConst.success("Healed " + player.getName() + "!").send(sender);
        FConst.info("You have been healed!").send(player);
    }
}
