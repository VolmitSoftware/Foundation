package com.volmit.foundation.bukkit.commands.positionals;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.World;

@Command("top")
@Permission("foundation.teleport.top")
public class CommandTop implements FCommand {
    @Default
    @Permission("foundation.teleport.top")
    public static void top(CommandSender sender) {
        if (sender instanceof Player p) {
            World world = p.getWorld();
            Location currentLocation = p.getLocation();
            int x = currentLocation.getBlockX();
            int z = currentLocation.getBlockZ();
            int y = world.getHighestBlockYAt(x, z);
            Location highestPoint = new Location(world, x, y, z);
            p.teleport(highestPoint);
            FConst.success("Teleported to the highest point!").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
