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

@Command("gmsp")
@Permission("foundation.gamemode.spectator")
public class CommandGameModeSpectator implements FCommand {
    @Default
    @Permission("foundation.gamemode.spectator.others")
    public static void gmsp(CommandSender sender, @APlayerArgument Player player) {
        CommandGameMode.gm(sender, player, "spectator");
    }

    @Default
    @Permission("foundation.gamemode.spectator.self")
    public static void gmsp(Player player) {
        CommandGameMode.gm(player, "spectator");
    }
}
