package com.volmit.foundation.bukkit.impl;

import com.volmit.foundation.api.Pos;
import org.bukkit.Location;

public class BukkitPos {
    public static Location toLocation(Pos pos) {
        return new Location(((BukkitWorld) pos.world()).getWorld(), pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    public static Pos toPos(Location location) {
        return Pos.builder()
                .x(location.getX())
                .y(location.getY())
                .z(location.getZ())
                .yaw(location.getYaw())
                .pitch(location.getPitch())
                .world(new BukkitWorld(location.getWorld()))
                .build();
    }
}
