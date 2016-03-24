package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.utils.IdGenerator;

import java.util.List;

public class Game {

    private final String gameId;
    private final GameConfig gameConfig;
    private final GameField gameField;
    private final List<Player> playerList;
    private boolean over;

    public Game(GameConfig gameConfig, GameField gameField, List<Player> playerList) {
        this.gameConfig = gameConfig;
        this.gameField = gameField;
        this.playerList = playerList;
        this.gameId = IdGenerator.gameId();
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public String getGameId() {
        return gameId;
    }

    public boolean isOver() {
        return false;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
