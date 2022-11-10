package com.volmit.foundation.bukkit.commands;

import com.volmit.foundation.bukkit.util.FCommand;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("gmc")
@Permission("foundation.gamemode.creative")
public class CommandGameModeCreative implements FCommand {
    @Default
    @Permission("foundation.gamemode.creative.others")
    public static void gmc(CommandSender sender, @APlayerArgument Player player) {
        CommandGameMode.gm(sender, player, "creative");
    }

    @Default
    @Permission("foundation.gamemode.creative.self")
    public static void gmc(Player player) {
        CommandGameMode.gm(player, "creative");
    }
}
