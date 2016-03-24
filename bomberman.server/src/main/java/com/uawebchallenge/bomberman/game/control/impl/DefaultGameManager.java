package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.control.GameRunner;
import com.uawebchallenge.bomberman.game.model.*;
import com.uawebchallenge.bomberman.game.utils.GameFieldGenerator;
import com.uawebchallenge.bomberman.game.utils.JsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class DefaultGameManager implements GameManager {

    private Map<String, Game> games = new HashMap<>();

    private final GameRunner gameRunner;

    @Autowired
    public DefaultGameManager(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
        createNewGame();
    }

    public Game createNewGame() {
        GameConfig gameConfig = GameConfig.DEFAULT;

        GameField gameField = GameFieldGenerator.generateGameField(gameConfig.getFieldSize());
        // TODO Add bots for the player
        Player player = new Player(gameConfig, 0, 0, PlayerType.HUMAN);
        List<Player> playerList = new LinkedList<>();
        playerList.add(player);

        Game game = new Game(gameConfig, gameField, playerList);

        games.put(game.getGameId(), game);
        gameRunner.run(game);

        return game;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }
}
