package com.uawebchallenge.bomberman.game.model;

import lombok.Value;

@Value
public class GameConfig {

    private final int timeBetweenFrames;
    private final double playerSpeed;
    private final int fieldSize;
    private final int bombTickDuration;
    private final int bombExplosionDuration;

    public GameConfig() {
        timeBetweenFrames = 50;
        playerSpeed = 0.004;
        bombTickDuration = 3000;
        bombExplosionDuration = 1000;
        fieldSize = 17;
    }
}
