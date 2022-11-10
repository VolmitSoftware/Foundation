package com.volmit.foundation.api;

import com.volmit.foundation.api.data.StoredLocation;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true, fluent = true)
@Builder
public class Pos {
    @Builder.Default
    private double x = 0;
    @Builder.Default
    private double y = 0;
    @Builder.Default
    private double z = 0;
    @Builder.Default
    private float yaw = 0;
    @Builder.Default
    private float pitch = 0;
    private FoundationWorld world;

    public StoredLocation store() {
        return new StoredLocation().world(world.getName()).x(x).y(y).z(z).yaw(yaw).pitch(pitch);
    }
}
