package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameMechanics;
import com.uawebchallenge.bomberman.game.control.GameRunner;
import com.uawebchallenge.bomberman.game.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class DefaultGameRunner implements GameRunner {

    private final GameMechanics gameMechanics;

    private final static Logger logger = LoggerFactory.getLogger(DefaultGameRunner.class);

    @Autowired
    public DefaultGameRunner(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    public void run(Game game) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final int time = game.getGameConfig().getTimeBetweenFrames();
        final GameRunnable gameRunnable = new GameRunnable(gameMechanics, game, scheduler);

        try {
            ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(gameRunnable, 0, time, TimeUnit.MILLISECONDS);
            handle.get();
        } catch (Exception e) {
            logger.error("Error while running game...", e);
        }
    }
}
