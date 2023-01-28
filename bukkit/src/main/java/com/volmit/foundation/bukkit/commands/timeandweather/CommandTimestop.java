package com.volmit.foundation.bukkit.commands.timeandweather;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("timetop")
@Alias({"tstop", "freezeworld", "lockout"})
@Permission("foundation.util.timestop")
public class CommandTimestop implements FCommand {
    @Default
    @Permission("foundation.util.timestop")
    public static void timestop(CommandSender sender) {
        if (sender instanceof Player p) {
            World world = p.getWorld();
            if (Boolean.TRUE.equals(world.getGameRuleValue(org.bukkit.GameRule.DO_DAYLIGHT_CYCLE))) {
                world.setGameRule(org.bukkit.GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setGameRule(org.bukkit.GameRule.DO_WEATHER_CYCLE, false);
            } else {
                world.setGameRule(org.bukkit.GameRule.DO_DAYLIGHT_CYCLE, true);
                world.setGameRule(org.bukkit.GameRule.DO_WEATHER_CYCLE, true);
            }
            FConst.success("Frozen the World! (Daylight / Weather)").send(sender);
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
