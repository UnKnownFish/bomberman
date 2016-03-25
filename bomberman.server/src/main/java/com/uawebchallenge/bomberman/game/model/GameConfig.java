package com.uawebchallenge.bomberman.game.model;

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

    public int getTimeBetweenFrames() {
        return timeBetweenFrames;
    }

    public double getPlayerSpeed() {
        return playerSpeed;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getBombTickDuration() {
        return bombTickDuration;
    }

    public int getBombExplosionDuration() {
        return bombExplosionDuration;
    }
}
