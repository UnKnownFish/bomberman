package com.uawebchallenge.bomberman.game.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import java.util.Timer;
import java.util.TimerTask;

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

    private final int idleIterationsThreshold;

    @Getter(AccessLevel.NONE)
    private final Timer timer;

    public GameConfig() {
        timeBetweenFrames = 50;
        playerSpeed = 0.004;
        bombTickDuration = 3000;
        bombExplosionDuration = 1000;
        fieldWidth = 13;
        fieldHeight = 11;
        maxBombs = 1;
        bombStrength = 2;

        // player game will timeout if he is not playing for 5 minutes
        idleIterationsThreshold = 5 * 60 * 1000 / timeBetweenFrames;

        timer = new Timer();
        timer.scheduleAtFixedRate(new IncreaseMaxBombs(this), 30000, 30000);
    }

    private class IncreaseMaxBombs extends TimerTask {

        private final GameConfig gameConfig;

        private IncreaseMaxBombs(GameConfig gameConfig) {
            this.gameConfig = gameConfig;
        }

        @Override
        public void run() {
            gameConfig.maxBombs = gameConfig.maxBombs + 1;
        }
    }
}
