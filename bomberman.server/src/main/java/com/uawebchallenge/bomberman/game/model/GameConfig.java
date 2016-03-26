package com.uawebchallenge.bomberman.game.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GameConfig {

    private final int timeBetweenFrames;
    private final double playerSpeed;
    private final int fieldWidth;
    private final int fieldHeight;

    private final int bombTickDuration;
    private final int bombExplosionDuration;
    private int maxBombs;
    private final int bombStrength;

    public GameConfig() {
        timeBetweenFrames = 50;
        playerSpeed = 0.004;
        bombTickDuration = 3000;
        bombExplosionDuration = 1000;
        fieldWidth = 13;
        fieldHeight = 11;
        maxBombs = 1;
        bombStrength = 2;
    }
}
