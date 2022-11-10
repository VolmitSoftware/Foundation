package com.volmit.foundation.bukkit;

import com.volmit.foundation.api.FoundationPlayer;
import com.volmit.foundation.api.FoundationServer;
import com.volmit.foundation.api.FoundationWorld;
import com.volmit.foundation.api.storage.CachedPlayerRepository;
import com.volmit.foundation.api.storage.FilePlayerRepository;
import com.volmit.foundation.bukkit.commands.CommandBack;
import com.volmit.foundation.bukkit.commands.CommandGameMode;
import com.volmit.foundation.bukkit.commands.CommandGameModeAdventure;
import com.volmit.foundation.bukkit.commands.CommandGameModeCreative;
import com.volmit.foundation.bukkit.commands.CommandGameModeSpectator;
import com.volmit.foundation.bukkit.commands.CommandGameModeSurvival;
import com.volmit.foundation.bukkit.service.PlayerService;
import com.volmit.foundation.bukkit.impl.BukkitPlayer;
import com.volmit.foundation.bukkit.impl.BukkitWorld;
import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FService;
import dev.jorel.commandapi.CommandAPI;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BukkitFoundation extends JavaPlugin implements FoundationServer
{
    public static BukkitFoundation instance;
    private List<FService> services = new ArrayList<>();
    private List<FCommand> commands = new ArrayList<>();
    public static BukkitAudiences audiences;
    private CachedPlayerRepository repository;

    @Override
    public void onEnable() {
        enabling();
        instance = this;
        repository = new CachedPlayerRepository(new FilePlayerRepository(new File(getDataFolder(), "playerdata")));
        audiences = BukkitAudiences.create(this);
        registerService(new PlayerService());
        registerCommand(new CommandBack());
        registerCommand(new CommandGameMode());
        registerCommand(new CommandGameModeCreative());
        registerCommand(new CommandGameModeAdventure());
        registerCommand(new CommandGameModeSpectator());
        registerCommand(new CommandGameModeSurvival());
    }

    @Override
    public void onDisable() {
        disabling();
        services.forEach(FService::stop);
    }

    private void registerCommand(FCommand command)
    {
        commands.add(command);
        CommandAPI.registerCommand(command.getClass());
    }

    private void registerService(FService service)
    {
        services.add(service);
        service.start();
    }

    @Override
    public CachedPlayerRepository getRepository() {
        return repository;
    }

    @Override
    public Stream<FoundationPlayer> streamPlayers() {
        return getServer().getOnlinePlayers().stream().map(BukkitPlayer::new);
    }

    @Override
    public Stream<FoundationWorld> streamWorlds() {
        return getServer().getWorlds().stream().map(BukkitWorld::new);
    }
}
