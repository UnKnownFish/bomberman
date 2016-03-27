package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.control.GameRunner;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DefaultGameManager implements GameManager {

    private final Map<String, Game> games = new HashMap<>();

    private final GameRunner gameRunner;

    @Autowired
    public DefaultGameManager(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    public Game createNewGame() {
        GameConfig gameConfig = new GameConfig();
        int fieldWidth = gameConfig.getFieldWidth();
        int fieldHeight = gameConfig.getFieldHeight();
        GameField gameField = new GameField(fieldWidth, fieldHeight);

        Player player1 = new Player(0, 0);
        Player player2 = new Player(fieldWidth - 1, 0);
        Player player3 = new Player(0, fieldHeight - 1);
        Player player4 = new Player(fieldWidth - 1, fieldHeight - 1);

        List<Player> playerList = new LinkedList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        Game game = new Game(gameConfig, gameField, playerList);
        games.put(game.getGameId(), game);
        return game;
    }

    public void startGame(Game game) {
        gameRunner.run(game);
    }

    public Optional<Game> getGame(String gameId) {
        return Optional.ofNullable(games.get(gameId));
    }
}
