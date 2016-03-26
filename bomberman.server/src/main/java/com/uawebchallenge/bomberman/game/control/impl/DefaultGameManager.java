package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.control.GameRunner;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        GameField gameField = new GameField(gameConfig.getFieldWidth(), gameConfig.getFieldHeight());
        // TODO Add bots for the player
        Player player = new Player(0, 0);
        List<Player> playerList = new LinkedList<>();
        playerList.add(player);

        Game game = new Game(gameConfig, gameField, playerList);
        games.put(game.getGameId(), game);
        return game;
    }

    public void startGame(Game game) {
        gameRunner.run(game);
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }
}
