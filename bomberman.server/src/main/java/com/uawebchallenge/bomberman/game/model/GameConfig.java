package com.uawebchallenge.bomberman.game.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GameConfig {

    public final static GameConfig DEFAULT =
            GameConfig.builder()
                    .playerSpeed(0.004)
                    .timeBetweenFrames(50)
                    .bombTickDuration(3000)
                    .bombExplosionDuration(1000)
                    .build();

    private final int timeBetweenFrames;
    private final double playerSpeed;
    private final int fieldSize;
    private final int bombTickDuration;
    private final int bombExplosionDuration;
}
