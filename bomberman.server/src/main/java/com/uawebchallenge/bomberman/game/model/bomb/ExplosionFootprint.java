package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.Position;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class ExplosionFootprint {

    private final int minX;
    private final int minY;
    private final int centerX;
    private final int centerY;
    private final int maxX;
    private final int maxY;

    public List<Position> getCoordinates() {
        // TODO implement me
        return Collections.singletonList(new Position(centerX, centerY));
    }
}
