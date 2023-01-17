package com.volmit.foundation.bukkit.commands.gamemode;

import com.volmit.foundation.bukkit.util.FCommand;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("gma")
@Permission("foundation.gamemode.adventure")
public class CommandGameModeAdventure implements FCommand {
    @Default
    @Permission("foundation.gamemode.adventure.others")
    public static void gma(CommandSender sender, @APlayerArgument Player player) {
        CommandGameMode.gm(sender, player, "adventure");
    }

    @Default
    @Permission("foundation.gamemode.adventure.self")
    public static void gma(Player player) {
        CommandGameMode.gm(player, "adventure");
    }
}
