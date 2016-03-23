package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.model.Config;
import com.uawebchallenge.bomberman.game.model.Game;

import java.util.HashMap;
import java.util.Map;

public class GamesManager {

    private static GamesManager INSTANCE = new GamesManager();

    private static Map<String, Game> games = new HashMap<>();

    public static GamesManager getInstance() {
        return INSTANCE;
    }

    public Game createNewGame() {
        Game game = new Game(Config.DEFAULT);
        // Generate default field
        // Add player and bot

        games.put(game.getGameId(), game);

        GameRunner gameThread = new GameRunner(game);
        gameThread.run();

        // Generate gameId and add game to hashMap

        return game;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }
}
