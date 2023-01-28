package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AEntityTypeArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@Command("killall")
@Alias({"ka", "ekill", "kall"})
@Permission("foundation.util.killall")
public class CommandKillall implements FCommand {

    @Default
    @Permission("foundation.util.killall")
    public static void killAll(CommandSender sender, @AEntityTypeArgument EntityType entityType) {
        int count = 0;
        if (sender instanceof Player p) {
            for (Entity e : p.getWorld().getEntities()) {
                if (e.getType() == entityType) {
                    if (e.getType() != EntityType.PLAYER) {
                        e.remove();
                        count++;
                    }
                }
            }
        } else {
            for (Player p : sender.getServer().getOnlinePlayers()) {
                for (Entity e : p.getWorld().getEntities()) {
                    if (e.getType() == entityType) {
                        if (e.getType() != EntityType.PLAYER) {
                            e.remove();
                            count++;
                        }
                    }
                }
            }
        }
        FConst.success("Killed " + count + " " + entityType.name() + "s.").send(sender);
    }

    @Default
    @Permission("foundation.util.all")
    public static void killAll(CommandSender sender) {
        int count = 0;
        if (sender instanceof Player p) {
            for (Entity e : p.getWorld().getEntities()) {
                if (e.getType() != EntityType.PLAYER) {
                    e.remove();
                    count++;
                }
            }
        } else {
            for (Player p : sender.getServer().getOnlinePlayers()) {
                for (Entity e : p.getWorld().getEntities()) {
                    if (e.getType() != EntityType.PLAYER) {
                        e.remove();
                        count++;
                    }
                }
            }
        }
        FConst.success("Killed " + count + " entities.").send(sender);

    }
}

