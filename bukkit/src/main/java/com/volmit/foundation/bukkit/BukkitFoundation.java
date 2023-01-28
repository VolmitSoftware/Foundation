package com.volmit.foundation.bukkit;

import com.volmit.foundation.api.FoundationPlayer;
import com.volmit.foundation.api.FoundationServer;
import com.volmit.foundation.api.FoundationWorld;
import com.volmit.foundation.api.storage.CachedPlayerRepository;
import com.volmit.foundation.api.storage.FilePlayerRepository;
import com.volmit.foundation.bukkit.commands.gamemode.*;
import com.volmit.foundation.bukkit.commands.items.*;
import com.volmit.foundation.bukkit.commands.positionals.CommandBack;
import com.volmit.foundation.bukkit.commands.positionals.CommandTop;
import com.volmit.foundation.bukkit.commands.timeandweather.*;
import com.volmit.foundation.bukkit.commands.utility.*;
import com.volmit.foundation.bukkit.impl.BukkitPlayer;
import com.volmit.foundation.bukkit.impl.BukkitWorld;
import com.volmit.foundation.bukkit.service.PlayerService;
import com.volmit.foundation.bukkit.util.C;
import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FService;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BukkitFoundation extends JavaPlugin implements FoundationServer {
    public static BukkitFoundation instance;
    public static BukkitAudiences audiences;
    private final List<FService> services = new ArrayList<>();
    private final List<FCommand> commands = new ArrayList<>();
    private CachedPlayerRepository repository;

    public static void msg(String string) {
        try {
            if (instance == null) {
                System.out.println("[Foundation]: " + string);
                return;
            }

            String msg = C.GRAY + "[" + C.GOLD + "Foundation" + C.GRAY + "]: " + string;
            Bukkit.getConsoleSender().sendMessage(msg);
        } catch (Throwable e) {
            System.out.println("[Foundation]: " + string);
        }
    }

    public static void actionbar(Player p, String msg) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
    }

    public static void warn(String string) {
        msg(C.YELLOW + string);
    }

    public static void error(String string) {
        msg(C.RED + string);
    }

    public static void info(String string) {
        msg(C.WHITE + string);
    }

    @Override
    public void onLoad() {
        // https://commandapi.jorel.dev/8.7.0/shading.html#loading
        CommandAPI.onLoad(new CommandAPIConfig().verboseOutput(false));
    }

    @Override
    public void onEnable() {
        enabling();
        instance = this;
        CommandAPI.onEnable(this);
        repository = new CachedPlayerRepository(new FilePlayerRepository(new File(getDataFolder(), "playerdata")));
        audiences = BukkitAudiences.create(this);
        registerService(new PlayerService());
        //Gamemode
        registerCommand(new CommandGameMode());
        registerCommand(new CommandGameModeCreative());
        registerCommand(new CommandGameModeAdventure());
        registerCommand(new CommandGameModeSpectator());
        registerCommand(new CommandGameModeSurvival());

        //Items
        registerCommand(new CommandGiveItem());
        registerCommand(new CommandMoreItem());

        //Positions
        registerCommand(new CommandBack());
        registerCommand(new CommandTop());

        //Time & Weather
        registerCommand(new CommandDay());
        registerCommand(new CommandNight());
        registerCommand(new CommandRain());
        registerCommand(new CommandTime());
        registerCommand(new CommandTimestop());

        //Utility
        registerCommand(new CommandEnderchest());
        registerCommand(new CommandFeed());
        registerCommand(new CommandFly());
        registerCommand(new CommandGod());
        registerCommand(new CommandHeal());
        registerCommand(new CommandKickall());
        registerCommand(new CommandKillall());
        registerCommand(new CommandRepair());
        registerCommand(new CommandTPAll());
        registerCommand(new CommandSummon());
        registerCommand(new CommandSuicide());

        info("All Foundation Commands Registered!");
        splashscreen();
    }

    @Override
    public void onDisable() {
        disabling();
        CommandAPI.onDisable();
        services.forEach(FService::stop);
    }

    private void registerCommand(FCommand command) {
        commands.add(command);
        CommandAPI.registerCommand(command.getClass());
    }

    private void registerService(FService service) {
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

    private static void splashscreen() {
        info("\n" +
                C.DARK_PURPLE + "·" + C.GOLD + "▄▄▄      ▄" + C.DARK_PURPLE + "•" + C.GOLD + " ▄▌ ▐ ▄ " + C.DARK_PURPLE + "·" + C.GOLD + "▄▄▄▄   ▄▄▄" + C.DARK_PURPLE + "·" + C.GOLD + " ▄▄▄▄▄" + C.DARK_PURPLE + "▪" + C.GOLD + "         ▐ ▄ \n" +
                C.GOLD + "▐▄▄" + C.DARK_PURPLE + "·▪     " + C.GOLD + "█" + C.DARK_PURPLE + "▪" + C.GOLD + "██▌" + C.DARK_PURPLE + "•" + C.GOLD + "█▌▐███" + C.DARK_PURPLE + "▪" + C.GOLD + " ██ ▐█ ▀█ " + C.DARK_PURPLE + "•" + C.GOLD + "██  ██ " + C.DARK_PURPLE + "▪     •" + C.GOLD + "█▌▐█\n" +
                C.GOLD + "██" + C.DARK_PURPLE + "▪  " + C.GOLD + "▄█▀▄ █▌▐█▌▐█▐▐▌▐█" + C.DARK_PURPLE + "·" + C.GOLD + " ▐█▌▄█▀▀█  ▐█" + C.DARK_PURPLE + ".▪" + C.GOLD + "▐█" + C.DARK_PURPLE + "· " + C.GOLD + "▄█▀▄ ▐█▐▐▌    By:  "+ C.RED + "A" + C.GOLD + "r" + C.YELLOW + "c" + C.GREEN + "a" + C.DARK_GRAY + "n" + C.AQUA + "e " + C.AQUA + "A" + C.BLUE + "r" + C.DARK_BLUE + "t" + C.DARK_PURPLE + "s" + C.WHITE + "\n" +
                C.GOLD + "██▌" + C.DARK_PURPLE + "." + C.GOLD + "▐█▌" + C.DARK_PURPLE + "." + C.GOLD + "▐▌▐█▄█▌██▐█▌██" + C.DARK_PURPLE + ". " + C.GOLD + "██ ▐█ " + C.DARK_PURPLE + "▪" + C.GOLD + "▐▌ ▐█▌" + C.DARK_PURPLE + "·" + C.GOLD + "▐█▌▐█▌" + C.DARK_PURPLE + "." + C.GOLD + "▐▌██▐█▌\n" +
                C.GOLD + "▀▀▀  ▀█▄▀" + C.DARK_PURPLE + "▪ " + C.GOLD + "▀▀▀ ▀▀ █" + C.DARK_PURPLE + "▪" + C.GOLD + "▀▀▀▀▀" + C.DARK_PURPLE + "•" + C.GOLD + "  ▀  ▀  ▀▀▀ ▀▀▀ ▀█▄▀" + C.DARK_PURPLE + "▪" + C.GOLD + "▀▀ █" + C.DARK_PURPLE + "▪\n");
    }

}
