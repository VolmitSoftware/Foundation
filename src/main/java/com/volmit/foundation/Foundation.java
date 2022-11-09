package com.volmit.foundation;

import com.volmit.foundation.commands.CommandGameMode;
import com.volmit.foundation.commands.CommandGameModeAdventure;
import com.volmit.foundation.commands.CommandGameModeCreative;
import com.volmit.foundation.commands.CommandGameModeSpectator;
import com.volmit.foundation.commands.CommandGameModeSurvival;
import com.volmit.foundation.util.FCommand;
import com.volmit.foundation.util.FService;
import dev.jorel.commandapi.CommandAPI;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Foundation extends JavaPlugin
{
    private List<FCommand> commands = new ArrayList<>();
    public static BukkitAudiences audiences;

    @Override
    public void onEnable() {
        audiences = BukkitAudiences.create(this);
        registerCommand(new CommandGameMode());
        registerCommand(new CommandGameModeCreative());
        registerCommand(new CommandGameModeAdventure());
        registerCommand(new CommandGameModeSpectator());
        registerCommand(new CommandGameModeSurvival());
    }

    @Override
    public void onDisable() {

    }

    private void registerCommand(FCommand command)
    {
        commands.add(command);
        CommandAPI.registerCommand(command.getClass());
    }
}
