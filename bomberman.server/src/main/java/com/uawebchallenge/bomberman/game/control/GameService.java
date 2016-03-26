package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.model.player.PlayerCommand;

public interface GameService {

    NewGameData createNewGame();

    void addCommand(String gameId, String playerId, PlayerCommand playerCommand);
}
