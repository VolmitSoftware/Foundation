package com.volmit.foundation.api;

import com.volmit.foundation.api.storage.CachedPlayerRepository;

import java.util.stream.Stream;

public interface FoundationServer {
    CachedPlayerRepository getRepository();

    Stream<FoundationPlayer> streamPlayers();

    Stream<FoundationWorld> streamWorlds();

    default void enabling() {
        Foundation.server = this;
    }

    default void disabling() {
        streamPlayers().forEach(FoundationPlayer::onDisable);
        getRepository().saveAll();
    }

    default FoundationWorld getWorld(String world) {
        return streamWorlds().filter(i -> i.getName().equals(world)).findFirst().orElse(null);
    }
}
