package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.utils.IdGenerator;

public class Game {

    private final Config config;

    private final String gameId;

    public Game(Config config) {
        this.config = config;
        this.gameId = IdGenerator.gameId();
    }

    public Config getConfig() {
        return config;
    }

    public String getGameId() {
        return gameId;
    }

    public boolean isOver() {
        return false;
    }

    public String toJson() {
        return null;
    }
}
