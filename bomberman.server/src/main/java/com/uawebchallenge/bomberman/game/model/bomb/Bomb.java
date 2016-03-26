package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.GameConfig;

public class Bomb {

    private final GameConfig gameConfig;
    private final int positionX;
    private final int positionY;
    private int timer;
    private ExplosionFootprint explosionFootprint;


    public Bomb(int positionX, int positionY, GameConfig gameConfig) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameConfig = gameConfig;
        this.timer = 0;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void updateTimer() {
        timer = timer + gameConfig.getTimeBetweenFrames();
    }

    public void setExplosionFootprint(ExplosionFootprint explosionFootprint) {
        this.explosionFootprint = explosionFootprint;
    }

    public boolean hasExplosionFootprint() {
        return explosionFootprint != null;
    }

    public boolean isExploding() {
        return timer > gameConfig.getBombTickDuration();
    }

    public int getProgress() {
        return (timer * 100) / gameConfig.getBombTickDuration();
    }

    public boolean isActive() {
        return timer < (gameConfig.getBombTickDuration() + gameConfig.getBombExplosionDuration());
    }
}
