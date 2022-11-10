package com.volmit.foundation.api.data;

import com.volmit.foundation.api.Pos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoundationPlayerData {
    @Singular
    private List<StoredLocation> backLocations = new ArrayList<>();
    private Map<String, StoredLocation> homes = new HashMap<>();
    private boolean ignoringNextBackTeleport = false;

    public boolean hasBack() {
        return !backLocations.isEmpty();
    }

    public Pos popBack() {
        if(backLocations.isEmpty()) {
            return null;
        }

        return backLocations.remove(backLocations.size() - 1).pos();
    }

    public void addBack(Pos pos) {
        backLocations.add(pos.store());
        if(backLocations.size() > 16) {
            backLocations.remove(0);
        }
    }
}
