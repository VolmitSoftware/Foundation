package com.volmit.foundation.commands;

import com.volmit.foundation.util.FCommand;
import com.volmit.foundation.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("gms")
@Permission("foundation.gamemode.survival")
public class CommandGameModeSurvival implements FCommand {
    @Default
    @Permission("foundation.gamemode.survival.others")
    public static void gms(CommandSender sender, @APlayerArgument Player player) {
        CommandGameMode.gm(sender, player, "survival");
    }

    @Default
    @Permission("foundation.gamemode.survival.self")
    public static void gms(Player player) {
        CommandGameMode.gm(player, "survival");
    }
}
