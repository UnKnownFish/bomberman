package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.control.GameRunner;
import com.uawebchallenge.bomberman.game.model.Config;
import com.uawebchallenge.bomberman.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultGameManager implements GameManager {

    private Map<String, Game> games = new HashMap<>();

    private final GameRunner gameRunner;

    @Autowired
    public DefaultGameManager(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    public Game createNewGame() {
        Game game = new Game(Config.DEFAULT);
        // Generate default field
        // Add player and bot

        games.put(game.getGameId(), game);
        gameRunner.run(game);

        return game;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }
}
