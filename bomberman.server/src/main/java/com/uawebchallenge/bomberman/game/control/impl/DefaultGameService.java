package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.control.GameService;
import com.uawebchallenge.bomberman.game.control.NewGameData;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.player.Player;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommand;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommandExecutor;
import com.uawebchallenge.bomberman.game.model.player.PlayerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultGameService implements GameService {

    private final GameManager gameManager;

    private final PlayerCommandExecutor playerCommandExecutor;

    @Autowired
    public DefaultGameService(GameManager gameManager) {
        this.gameManager = gameManager;
        playerCommandExecutor = new PlayerCommandExecutor();
    }

    @Override
    public NewGameData createNewGame() {
        Game newGame = gameManager.createNewGame();
        Player player = newGame.connectHuman();
        GameConfig gameConfig = newGame.getGameConfig();
        gameManager.startGame(newGame);
        return new NewGameData(newGame.getGameId(), player.getPlayerId(), gameConfig.getFieldWidth(), gameConfig.getFieldHeight());
    }

    @Override
    public NewGameData connectToGame(String gameId) {
        Game game = gameManager.getGame(gameId);
        Player player = game.connectHuman();
        GameConfig gameConfig = game.getGameConfig();
        return new NewGameData(game.getGameId(), player.getPlayerId(), gameConfig.getFieldWidth(), gameConfig.getFieldHeight());
    }

    @Override
    public void addCommand(String gameId, String playerId, PlayerCommand playerCommand) {
        Game game = gameManager.getGame(gameId);
        Player player = game.getPlayer(playerId);
        PlayerState newPlayerState = playerCommandExecutor.execute(playerCommand, player.getCurrentPlayerState(), game.getGameField(), game.getGameConfig());
        player.setNextPlayerState(newPlayerState);
    }
}
