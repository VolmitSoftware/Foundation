package com.volmit.foundation.bukkit.commands.positionals;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import dev.jorel.commandapi.annotations.arguments.AWorldArgument;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


@Command("world")
@Permission("foundation.teleport.world")
public class CommandWorld implements FCommand {
    @Default
    @Permission("foundation.teleport.world")
    public static void world(CommandSender sender, @AWorldArgument World world) {
        if (sender instanceof Player p) {
            p.teleport(world.getSpawnLocation());
            FConst.success("Teleported to world '" + world.getName() + "'!").send(p);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }

    @Default
    @Permission("foundation.teleport.world.others")
    public static void world(CommandSender sender, @AWorldArgument World worldname, @APlayerArgument Player target) {
        target.teleport(worldname.getSpawnLocation());
        FConst.success("Teleported " + target.getName() + " to world '" + worldname.getName() + "'!").send(sender);
        FConst.info("You have been teleported to world '" + worldname.getName() + "'!").send(target);
    }
}
