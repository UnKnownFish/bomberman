package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;

import java.util.Optional;

class ExplosionFootprintBuilder {

    public ExplosionFootprint build(int bombX, int bombY, int bombStrength, GameField gameField) {
        int minX = findMinX(gameField, bombX, bombX - bombStrength, bombY);
        int maxX = findMaxX(gameField, bombX, bombX + bombStrength, bombY);
        int minY = findMinY(gameField, bombY, bombY - bombStrength, bombX);
        int maxY = findMaxY(gameField, bombY, bombY + bombStrength, bombX);

        return new ExplosionFootprint(minX, minY, bombX, bombY, maxX, maxY);
    }

    private int findMinX(GameField gameField, int rangeStart, int rangeEnd, int y) {
        for (int x = rangeStart; x >= rangeEnd; x--) {
            Optional<GameFieldItem> fieldItem = gameField.getFieldItem(x, y);
            if (isExplosionTarget(fieldItem)) {
                return x;
            }
            if (isExplosionForbidden(fieldItem)) {
                return x + 1;
            }
        }
        return rangeEnd;
    }

    private int findMaxX(GameField gameField, int rangeStart, int rangeEnd, int y) {
        for (int x = rangeStart; x <= rangeEnd; x++) {
            Optional<GameFieldItem> fieldItem = gameField.getFieldItem(x, y);
            if (isExplosionTarget(fieldItem)) {
                return x;
            }
            if (isExplosionForbidden(fieldItem)) {
                return x - 1;
            }
        }
        return rangeEnd;
    }

    private int findMinY(GameField gameField, int rangeStart, int rangeEnd, int x) {
        for (int y = rangeStart; y >= rangeEnd; y--) {
            Optional<GameFieldItem> fieldItem = gameField.getFieldItem(x, y);
            if (isExplosionTarget(fieldItem)) {
                return y;
            }
            if (isExplosionForbidden(fieldItem)) {
                return y + 1;
            }
        }
        return rangeEnd;
    }

    private int findMaxY(GameField gameField, int rangeStart, int rangeEnd, int x) {
        for (int y = rangeStart; y <= rangeEnd; y++) {
            Optional<GameFieldItem> fieldItem = gameField.getFieldItem(x, y);
            if (isExplosionTarget(fieldItem)) {
                return y;
            }
            if (isExplosionForbidden(fieldItem)) {
                return y - 1;
            }
        }
        return rangeEnd;
    }

    private boolean isExplosionTarget(Optional<GameFieldItem> gameFieldItemOptional) {
        return gameFieldItemOptional.isPresent() && gameFieldItemOptional.get() == GameFieldItem.BLOCK;
    }

    private boolean isExplosionForbidden(Optional<GameFieldItem> gameFieldItemOptional) {
        return !gameFieldItemOptional.isPresent() || gameFieldItemOptional.get() == GameFieldItem.STONE;
    }
}
