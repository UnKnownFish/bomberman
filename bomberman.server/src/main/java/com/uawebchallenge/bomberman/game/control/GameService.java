package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.exception.BombermanException;

public interface GameService {

    NewGameData createNewGame() throws BombermanException;

    void addCommand(String gameId, String playerId, String command) throws BombermanException;

    NewGameData connectToGame(String gameId) throws BombermanException;
}
