package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameMechanics;
import com.uawebchallenge.bomberman.game.model.Game;

import java.util.concurrent.ExecutorService;

public class GameRunnable implements Runnable {

    private final ExecutorService executorService;
    private final GameMechanics gameMechanics;
    private final Game game;

    public GameRunnable(GameMechanics gameMechanics, Game game, ExecutorService executorService) {
        this.gameMechanics = gameMechanics;
        this.game = game;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        if (game.isOver()) {
            executorService.shutdown();
        }
    }
}
