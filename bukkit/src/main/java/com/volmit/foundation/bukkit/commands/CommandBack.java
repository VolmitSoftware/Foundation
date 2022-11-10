package com.volmit.foundation.bukkit.commands;

import com.volmit.foundation.bukkit.impl.BukkitPlayer;
import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import com.volmit.foundation.bukkit.util.Feedback;
import com.volmit.foundation.bukkit.util.SoundFeedback;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("back")
@Alias({"bak"})
@Permission("foundation.back")
public class CommandBack implements FCommand {
    @Default
    public static void back(Player player) {
        if(new BukkitPlayer(player).goBack())
        {
            FConst.success("Teleported to your previous position").send(player);
        }

        else
        {
            FConst.error("You have gone back as far as you can!").send(player);
        }
    }
}
