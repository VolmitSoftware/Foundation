package com.volmit.foundation.bukkit.commands.utility;


import com.volmit.foundation.bukkit.util.FCommand;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AEntityTypeArgument;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@Command("summon")
@Permission("foundation.util.summon")
public class CommandSummon implements FCommand {

    @Default
    @Permission("foundation.util.summon")
    public static void summon(CommandSender sender, @AEntityTypeArgument EntityType entityType) {
        if (sender instanceof Player player) {
            World world = player.getWorld();
            Location entityLoc = player.getTargetBlockExact(100).getLocation();
            world.spawnEntity(entityLoc, entityType);
            player.sendMessage("Summoned a " + entityType.name() + " at " + entityLoc);
        }
    }
}
