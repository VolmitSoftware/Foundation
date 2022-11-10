package com.volmit.foundation.api.storage;

import com.volmit.foundation.api.data.FoundationPlayerData;

import java.io.IOException;
import java.util.UUID;

public class BlackHolePlayerRepository implements PlayerRepository {
    @Override
    public void delete(UUID id) {

    }

    @Override
    public boolean exists(UUID id) {
        return false;
    }

    @Override
    public FoundationPlayerData load(UUID id) throws IOException {
        return new FoundationPlayerData();
    }

    @Override
    public void save(UUID id, FoundationPlayerData player) throws IOException {

    }

    @Override
    public void save(UUID id) {

    }
}
