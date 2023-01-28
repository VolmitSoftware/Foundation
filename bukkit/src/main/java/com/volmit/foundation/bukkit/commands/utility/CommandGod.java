package com.volmit.foundation.bukkit.commands.utility;

import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.entity.Player;

@Command("god")
@Alias({"godmode", "invulnerable", "invuln"})
@Permission("foundation.util.god")
public class CommandGod implements FCommand {
    @Default
    @Permission("foundation.util.god")
    public static void god(Player player) {
        player.setInvulnerable(!player.isInvulnerable());
        if(player.isInvulnerable()) {
            FConst.success("God mode enabled!").send(player);
        } else {
            FConst.success("God mode disabled!").send(player);
        }
    }
    @Default
    @Permission("foundation.util.god.others")
    public static void god(Player sender, @APlayerArgument Player target) {
        target.setInvulnerable(!target.isInvulnerable());
        if(target.isInvulnerable()) {
            FConst.success("God mode enabled for " + target.getName() + "!").send(sender);
            FConst.info("God mode enabled!").send(target);
        } else {
            FConst.success("God mode disabled for " + target.getName() + "!").send(sender);
            FConst.info("God mode disabled!").send(target);
        }
    }
}
