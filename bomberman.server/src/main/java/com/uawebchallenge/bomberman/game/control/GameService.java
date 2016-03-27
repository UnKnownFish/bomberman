package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.exception.BombermanException;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommand;

public interface GameService {

    NewGameData createNewGame() throws BombermanException;

    void addCommand(String gameId, String playerId, PlayerCommand playerCommand) throws BombermanException;

    NewGameData connectToGame(String gameId) throws BombermanException;
}
