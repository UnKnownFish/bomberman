package com.uawebchallenge.bomberman.game.model;

public class Bomb {
    private final int positionX;
    private final int positionY;
    private final int tickDuration;
    private final int explosionDuration;
    private int timer;

    public Bomb(int positionX, int positionY, int tickDuration, int explosionDuration) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.tickDuration = tickDuration;
        this.explosionDuration = explosionDuration;
        this.timer = 0;
    }
}
