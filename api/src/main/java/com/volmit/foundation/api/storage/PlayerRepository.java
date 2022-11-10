package com.volmit.foundation.api.storage;

import com.volmit.foundation.api.FoundationPlayer;
import com.volmit.foundation.api.data.FoundationPlayerData;

import java.io.IOException;
import java.util.UUID;

public interface PlayerRepository {
    void delete(UUID id);

    boolean exists(UUID id);

    FoundationPlayerData load(UUID id) throws IOException;

    void save(UUID id, FoundationPlayerData player) throws IOException;

    void save(UUID id);
}
