package com.uawebchallenge.bomberman.game.model.bomb;

import lombok.Value;

@Value
public class ExplosionFootprint {

    private final int minX;
    private final int minY;
    private final int centerX;
    private final int centerY;
    private final int maxX;
    private final int maxY;
}
