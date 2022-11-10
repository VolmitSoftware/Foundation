package com.volmit.foundation.api;

import com.volmit.foundation.api.data.FoundationPlayerData;

import java.util.UUID;

public interface FoundationPlayer {
    default FoundationPlayerData getData() {
        return getServer().getRepository().load(getUUID());
    }

    default void onTeleport(Pos pos) {
        if(!getData().isIgnoringNextBackTeleport()) {
            getData().addBack(pos);
        }

        getData().setIgnoringNextBackTeleport(false);
    }

    default boolean goBack() {
        if(!getData().hasBack()) {
            return false;
        }

        getData().setIgnoringNextBackTeleport(true);

        try {
            teleport(getData().popBack());
            return true;
        }

        catch(Throwable e) {
            getData().setIgnoringNextBackTeleport(false);
            return false;
        }
    }

    int getGameMode();

    void setGameMode(int mode);

    String getName();

    UUID getUUID();

    FoundationServer getServer();

    FoundationWorld getWorld();

    Pos getPosition();

    void teleport(Pos position);

    default void onDisable() {
        Foundation.server.getRepository().save(getUUID());
    }
}
