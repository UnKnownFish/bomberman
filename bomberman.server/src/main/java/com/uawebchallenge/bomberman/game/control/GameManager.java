package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.model.Game;

public interface GameManager {

    Game createNewGame();

    Game getGame(String gameId);
}
