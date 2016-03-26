package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.player.Player;
import com.uawebchallenge.bomberman.game.model.player.PlayerType;
import com.uawebchallenge.bomberman.game.utils.IdGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {

    private final String gameId;
    private final GameConfig gameConfig;
    private final GameField gameField;
    private final List<Player> playerList;
    private final Map<String, Player> playerMap;
    private boolean over;

    public Game(GameConfig gameConfig, GameField gameField, List<Player> playerList) {
        this.gameConfig = gameConfig;
        this.gameField = gameField;
        this.playerList = playerList;
        this.playerMap = playerList.stream().collect(Collectors.toMap(Player::getPlayerId, p -> p));
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

    public void setOver() {
        this.over = true;
    }

    public boolean isOver() {
        return over;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Player getPlayer(String playerId) {
        return playerMap.get(playerId);
    }

    public Player connectHuman() {
        Player player = playerList.stream()
                .filter(p -> PlayerType.AI == p.getPlayerType())
                .findFirst()
                .get();

        if (player == null) {
            // So far game is only expecting 1 player to play it. But it will be very easy for other player to join same game
            // When I get to it - I will change code to use Optional<T> instead of throwing exception
            throw new IllegalStateException("There are no free players in this game. GameId=" + gameId);
        }

        player.setHuman();
        return player;
    }
}
