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

    public GameField getGameField() {
        return gameField;
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

    public Player connectHuman() {
        for (Player player : playerList) {
            if (PlayerType.AI == player.getPlayerType()) {
                player.setPlayerType(PlayerType.HUMAN);
                return player;
            }
        }

        // So far game is only expecting 1 player to play it. But it will be very easy for other player to join same game
        // When I get to it - I will change code to use Optional<T> instead of throwing exception
        throw new IllegalStateException("There are no free players in this game. GameId=" + gameId);
    }
}
