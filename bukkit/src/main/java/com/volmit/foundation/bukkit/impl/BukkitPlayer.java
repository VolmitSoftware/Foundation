package com.volmit.foundation.bukkit.impl;

import com.volmit.foundation.api.FoundationPlayer;
import com.volmit.foundation.api.FoundationServer;
import com.volmit.foundation.api.FoundationWorld;
import com.volmit.foundation.api.Pos;
import com.volmit.foundation.bukkit.BukkitFoundation;
import com.volmit.foundation.bukkit.util.FConst;
import lombok.Data;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Data
public class BukkitPlayer implements FoundationPlayer {
    private final Player player;
    private boolean ignoringNextBack;

    public BukkitPlayer(Player player) {
        this.player = player;
        this.ignoringNextBack = false;
    }

    @Override
    public int getGameMode() {
        return player.getGameMode().getValue();
    }

    @Override
    public void setGameMode(int mode) {
        player.setGameMode(GameMode.getByValue(mode));
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public java.util.UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public FoundationServer getServer() {
        return BukkitFoundation.instance;
    }

    @Override
    public FoundationWorld getWorld() {
        return new BukkitWorld(player.getWorld());
    }

    @Override
    public Pos getPosition() {
        return BukkitPos.toPos(player.getLocation());
    }

    @Override
    public void teleport(Pos position) {
        player.teleport(BukkitPos.toLocation(position));
        FConst.TELEPORT.send(player);
    }

    @Override
    public void onDisable() {
        FoundationPlayer.super.onDisable();
    }
}
