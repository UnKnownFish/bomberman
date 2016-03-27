package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.model.Game;

import java.util.Optional;

public interface GameManager {

    Game createNewGame();

    void startGame(Game game);

    Optional<Game> getGame(String gameId);
}
