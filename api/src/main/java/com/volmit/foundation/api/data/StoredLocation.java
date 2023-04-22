package com.volmit.foundation.api.data;

import com.volmit.foundation.api.Foundation;
import com.volmit.foundation.api.Pos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
public class StoredLocation {
    private String world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public Pos pos() {
        return Pos.builder().world(Foundation.server.getWorld(world)).x(x).y(y).z(z).yaw(yaw).pitch(pitch).build();
    }
}
