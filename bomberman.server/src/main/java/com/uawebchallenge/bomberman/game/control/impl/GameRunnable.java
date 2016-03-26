package com.uawebchallenge.bomberman.game.control.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uawebchallenge.bomberman.game.control.GameDataPush;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.GameMechanics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

class GameRunnable implements Runnable {

    private final ExecutorService executorService;
    private final GameMechanics gameMechanics;
    private final GameDataPush gameDataPush;
    private final Game game;
    private final static Logger logger = LoggerFactory.getLogger(GameRunnable.class);

    public GameRunnable(Game game, ExecutorService executorService, GameDataPush gameDataPush) {
        this.gameMechanics = new GameMechanics();
        this.game = game;
        this.executorService = executorService;
        this.gameDataPush = gameDataPush;
    }

    @Override
    public void run() {
        try {
            gameMechanics.updateGameState(game);

            String gameData = JsonBuilder.toJson(game);
            gameDataPush.push(game.getGameId(), gameData);

            if (game.isOver()) {
                executorService.shutdown();
            }
        } catch (JsonProcessingException e) {
            logger.error("Error parsing game object to JSON.", e);
        } catch (Exception e) {
            // I need to catch everything here otherwise my exceptions become invisible and thread stops
            logger.error("Error occurred.", e);
        }
    }
}
