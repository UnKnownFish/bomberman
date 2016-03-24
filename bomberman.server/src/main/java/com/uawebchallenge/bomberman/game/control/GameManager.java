package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.model.Game;

public interface GameManager {

    Game createNewGame();

    void startGame(Game game);

    Game getGame(String gameId);
}
