package com.uawebchallenge.bomberman.game.model;

public class PlayerStateValidator {

    static boolean isStateValid(PlayerState playerState, GameField gameField, GameConfig gameConfig) {
        int maxX = (int) Math.ceil(playerState.getPositionX());
        int maxY = (int) Math.ceil(playerState.getPositionY());

        GameFieldItem fieldItem = gameField.getFieldItem(maxX, maxY);

        if (fieldItem == GameFieldItem.STONE || fieldItem == GameFieldItem.BLOCK) {
            return false;
        }


        return true;
    }
}
