package com.volmit.foundation.api;

import java.io.File;
import java.util.UUID;
import java.util.stream.Stream;

public interface FoundationWorld {
    String getName();

    UUID getUUID();

    File getFolder();

    Stream<FoundationPlayer> streamPlayers();
}
