package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("feed")
@Permission("foundation.util.feed")
public class CommandFeed implements FCommand {
    @Default
    @Permission("foundation.util.feed")
    public static void feed(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(20);
        FConst.success("Fed!").send(player);
    }

    @Default
    @Permission("foundation.util.feed.others")
    public static void feed(CommandSender sender, @APlayerArgument Player target) {
        target.setFoodLevel(20);
        target.setSaturation(20);
        FConst.success("Healed " + target.getName() + "!").send(sender);
        FConst.info("You have been Fed!!").send(target);
    }
}
