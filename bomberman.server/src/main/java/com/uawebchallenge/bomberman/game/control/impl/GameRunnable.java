package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameMechanics;
import com.uawebchallenge.bomberman.game.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

public class GameRunnable implements Runnable {

    private final ExecutorService executorService;
    private final GameMechanics gameMechanics;
    private final Game game;
    private final static Logger logger = LoggerFactory.getLogger(GameRunnable.class);

    public GameRunnable(GameMechanics gameMechanics, Game game, ExecutorService executorService) {
        this.gameMechanics = gameMechanics;
        this.game = game;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            gameMechanics.updateGameState(game);

            if (game.isOver()) {
                executorService.shutdown();
            }
        } catch (Exception e) {
            // I need to catch everything here otherwise my exceptions become invisible and thread stops
            logger.error("Error occurred.", e);
        }
    }
}
