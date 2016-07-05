package com.uawebchallenge.bomberman.game.model.player;

import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.bomb.Bomb;
import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;

import java.util.Optional;

class PlayerStateValidator {

    public boolean isStateValid(PlayerCommand playerCommand, PlayerState playerState, GameField gameField, GameConfig gameConfig) {
        return isBombValid(playerState, gameConfig) && isPositionValid(playerCommand, playerState, gameField);
    }

    private boolean isBombValid(PlayerState playerState, GameConfig gameConfig) {
        long activeBombs = playerState.getBombs().stream().filter(Bomb::isActive).count();
        return activeBombs <= gameConfig.getMaxBombs();
    }

    private boolean isPositionValid(PlayerCommand playerCommand, PlayerState playerState, GameField gameField) {
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

        return !(!fieldItemOptional.isPresent() ||
                fieldItemOptional.get() == GameFieldItem.STONE ||
                fieldItemOptional.get() == GameFieldItem.BLOCK);
    }
}
