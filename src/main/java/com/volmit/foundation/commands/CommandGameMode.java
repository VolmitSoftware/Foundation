package com.volmit.foundation.commands;

import com.volmit.foundation.util.FCommand;
import com.volmit.foundation.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.ALiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("gm")
@Alias({"gmode", "mode"})
@Permission("foundation.gamemode")
public class CommandGameMode implements FCommand {
    @Default
    @Permission("foundation.gamemode.others")
    public static void gm(CommandSender sender,
                           @APlayerArgument Player player,
                           @AMultiLiteralArgument({"creative", "survival", "adventure", "spectator"}) String gamemode) {
        player.setGameMode(GameMode.valueOf(gamemode.toUpperCase()));
        FConst.success("Set " + player.getName() + "'s gamemode to " + gamemode).send(sender);
        FConst.info("Set gamemode to "  + gamemode).send(player);
    }

    @Default
    @Permission("foundation.gamemode.self")
    public static void gm(Player player, @AMultiLiteralArgument({"creative", "survival", "adventure", "spectator"}) String gamemode) {
        player.setGameMode(GameMode.valueOf(gamemode.toUpperCase()));
        FConst.success("Set gamemode to " + gamemode).send(player);
    }
}
