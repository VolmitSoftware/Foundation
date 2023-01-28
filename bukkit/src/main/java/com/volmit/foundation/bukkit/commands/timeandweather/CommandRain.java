package com.volmit.foundation.bukkit.commands.timeandweather;


import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("rain")
@Permission("foundation.util.rain")
public class CommandRain implements FCommand {
    @Default
    @Permission("foundation.util.rain")
    public static void rain(CommandSender sender, @AMultiLiteralArgument({"off", "on"}) String state) {
        if (sender instanceof Player p) {
            if (state.equalsIgnoreCase("off")) {
                p.getWorld().setStorm(false);
                FConst.success("Turned off rain").send(sender);
            } else if (state.equalsIgnoreCase("on")) {
                p.getWorld().setStorm(true);
                FConst.success("Turned on rain").send(sender);
            }
        } else {
            sender.sendMessage("You must be a player to use this command.");
            FConst.error("You must be a player to use this command.").send(sender);
        }
    }
}
