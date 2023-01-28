package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.entity.Player;

@Command("fly")
@Permission("foundation.util.fly")
public class CommandFly implements FCommand {
    @Default
    public static void fly(Player player) {
        player.setAllowFlight(!player.getAllowFlight());
        if(player.getAllowFlight()) {
            FConst.success("Fly mode enabled!").send(player);
        } else {
            FConst.success("Fly mode disabled!").send(player);
        }
    }
    @Default
    @Permission("foundation.util.fly.others")
    public static void fly(Player sender, @APlayerArgument Player target) {
        target.setAllowFlight(!target.getAllowFlight());
        if(target.getAllowFlight()) {
            FConst.success("Fly mode enabled for " + target.getName() + "!").send(sender);
            FConst.info("Fly mode enabled!").send(target);
        } else {
            FConst.success("Fly mode disabled for " + target.getName() + "!").send(sender);
            FConst.info("Fly mode disabled!").send(target);
        }
    }
}
