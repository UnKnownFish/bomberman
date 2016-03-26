package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameDataPush;
import com.uawebchallenge.bomberman.game.control.GameRunner;
import com.uawebchallenge.bomberman.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DefaultGameRunner implements GameRunner {

    private final GameDataPush gameDataPush;

    @Autowired
    public DefaultGameRunner(GameDataPush gameDataPush) {
        this.gameDataPush = gameDataPush;
    }

    public void run(Game game) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final int time = game.getGameConfig().getTimeBetweenFrames();
        final GameRunnable gameRunnable = new GameRunnable(game, scheduler, gameDataPush);
        scheduler.scheduleAtFixedRate(gameRunnable, 0, time, TimeUnit.MILLISECONDS);
    }
}
