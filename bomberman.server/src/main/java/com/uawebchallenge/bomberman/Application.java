package com.uawebchallenge.bomberman;

import com.uawebchallenge.bomberman.game.control.GamesManager;

public class Application {

    public static void main(String[] args) {
        GamesManager.getInstance().createNewGame();
    }
}
