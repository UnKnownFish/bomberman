package com.uawebchallenge.bomberman.game.model;

import java.util.Optional;

public class PlayerStateValidator {

    static boolean isStateValid(PlayerCommand playerCommand, PlayerState playerState, GameField gameField, GameConfig gameConfig) {
        double positionX = playerState.getPositionX();
        double positionY = playerState.getPositionY();

        int maxX = (int) positionX;
        int maxY = (int) positionY;

        if (playerCommand == PlayerCommand.DOWN) {
            maxY = (int) Math.ceil(positionY);
        } else if (playerCommand == PlayerCommand.UP) {
            maxY = (int) Math.floor(positionY);
        } else if (playerCommand == PlayerCommand.LEFT) {
            maxX = (int) Math.floor(positionX);
        } else if (playerCommand == PlayerCommand.RIGHT) {
            maxX = (int) Math.ceil(positionX);
        }

        Optional<GameFieldItem> fieldItemOptional = gameField.getFieldItem(maxX, maxY);

        if (!fieldItemOptional.isPresent() ||
                fieldItemOptional.get() == GameFieldItem.STONE ||
                fieldItemOptional.get() == GameFieldItem.BLOCK) {
            return false;
        }

        return true;
    }
}
