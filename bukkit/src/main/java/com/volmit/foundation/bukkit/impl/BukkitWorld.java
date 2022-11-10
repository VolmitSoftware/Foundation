package com.volmit.foundation.bukkit.impl;

import com.volmit.foundation.api.FoundationWorld;
import lombok.Data;

@Data
public class BukkitWorld implements FoundationWorld {
    private final org.bukkit.World world;

    public BukkitWorld(org.bukkit.World world) {
        this.world = world;
    }

    @Override
    public String getName() {
        return world.getName();
    }

    @Override
    public java.util.UUID getUUID() {
        return world.getUID();
    }

    @Override
    public java.io.File getFolder() {
        return world.getWorldFolder();
    }

    @Override
    public java.util.stream.Stream<com.volmit.foundation.api.FoundationPlayer> streamPlayers() {
        return world.getPlayers().stream().map(BukkitPlayer::new);
    }
}
