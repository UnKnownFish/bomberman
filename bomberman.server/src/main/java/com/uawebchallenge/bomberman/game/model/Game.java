package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.player.Player;
import com.uawebchallenge.bomberman.game.model.player.PlayerType;
import com.uawebchallenge.bomberman.game.utils.IdGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<Player> getPlayer(String playerId) {
        return Optional.ofNullable(playerMap.get(playerId));
    }

    public Optional<Player> connectHuman() {
        Optional<Player> playerOptional = playerList.stream()
                .filter(p -> PlayerType.AI == p.getPlayerType())
                .findFirst();

        if (playerOptional.isPresent()) {
            playerOptional.get().setHuman();
        }

        return playerOptional;
    }
}
