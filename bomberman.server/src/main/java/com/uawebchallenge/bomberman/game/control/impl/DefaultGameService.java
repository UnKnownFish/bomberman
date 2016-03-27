package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.control.GameService;
import com.uawebchallenge.bomberman.game.control.NewGameData;
import com.uawebchallenge.bomberman.game.exception.*;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.player.Player;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommand;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommandExecutor;
import com.uawebchallenge.bomberman.game.model.player.PlayerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public NewGameData createNewGame() throws BombermanException {
        Game newGame = gameManager.createNewGame();
        Player player = connectPlayer(newGame);
        GameConfig gameConfig = newGame.getGameConfig();
        gameManager.startGame(newGame);
        return new NewGameData(newGame.getGameId(), player.getPlayerId(), gameConfig.getFieldWidth(), gameConfig.getFieldHeight());
    }

    @Override
    public NewGameData connectToGame(String gameId) throws BombermanException {
        Game game = getGame(gameId);
        Player player = connectPlayer(game);
        GameConfig gameConfig = game.getGameConfig();
        return new NewGameData(game.getGameId(), player.getPlayerId(), gameConfig.getFieldWidth(), gameConfig.getFieldHeight());
    }

    @Override
    public void addCommand(String gameId, String playerId, PlayerCommand playerCommand) throws BombermanException {
        Game game = getGame(gameId);
        Player player = getPlayer(game, playerId);
        PlayerState newPlayerState = playerCommandExecutor.execute(playerCommand, player.getCurrentPlayerState(), game.getGameField(), game.getGameConfig());
        player.setNextPlayerState(newPlayerState);
    }

    private Game getGame(String gameId) throws GameNotFoundException, GameIsOverException {
        Optional<Game> gameOptional = gameManager.getGame(gameId);
        if (!gameOptional.isPresent()) {
            throw new GameNotFoundException("Couldn't find running game. GameId:" + gameId);
        }
        Game game = gameOptional.get();
        if (game.isOver()) {
            throw new GameIsOverException("Game is over. GameId:" + gameId);
        }
        return game;
    }

    private Player connectPlayer(Game game) throws PlayerNotConnectedException {
        Optional<Player> playerOptional = game.connectHuman();
        if (!playerOptional.isPresent()) {
            throw new PlayerNotConnectedException("Couldn't connect player. GameId:" + game.getGameId());
        }
        return playerOptional.get();
    }

    private Player getPlayer(Game game, String playerId) throws PlayerNotFoundException {
        Optional<Player> player = game.getPlayer(playerId);
        if (!player.isPresent()) {
            throw new PlayerNotFoundException("Player was not found. GameId:" + game.getGameId() +
                    ". PlayerId:" + playerId);
        }
        return player.get();
    }
}
