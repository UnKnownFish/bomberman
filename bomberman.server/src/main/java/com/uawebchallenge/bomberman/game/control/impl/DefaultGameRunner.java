package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameMechanics;
import com.uawebchallenge.bomberman.game.control.GameRunner;
import com.uawebchallenge.bomberman.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DefaultGameRunner implements GameRunner {

    private final GameMechanics gameMechanics;

    @Autowired
    public DefaultGameRunner(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    public void run(Game game) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final int time = game.getGameConfig().getTimeBetweenFrames();
        final GameRunnable gameRunnable = new GameRunnable(gameMechanics, game, scheduler);
        scheduler.scheduleAtFixedRate(gameRunnable, 0, time, TimeUnit.MILLISECONDS);
    }
}
