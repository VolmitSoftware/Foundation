package com.volmit.foundation.bukkit.service;

import com.volmit.foundation.api.Foundation;
import com.volmit.foundation.bukkit.BukkitFoundation;
import com.volmit.foundation.bukkit.impl.BukkitPlayer;
import com.volmit.foundation.bukkit.impl.BukkitPos;
import com.volmit.foundation.bukkit.util.FService;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerService implements Listener, FService {
    @Override
    public void start() {
        Bukkit.getPluginManager().registerEvents(this, BukkitFoundation.instance);
    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void on(PlayerTeleportEvent e) {
        new BukkitPlayer(e.getPlayer()).onTeleport(BukkitPos.toPos(e.getFrom()));
    }

    @EventHandler
    public void on(PlayerQuitEvent e) {
        Foundation.server.getRepository().save(e.getPlayer().getUniqueId());
    }
}
