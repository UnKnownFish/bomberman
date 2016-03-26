package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;

import java.util.Optional;

class ExplosionFootprintBuilder {

    public ExplosionFootprint build(int bombX, int bombY, int bombStrength, GameField gameField) {

        int minX = bombX;
        int maxX = bombX;
        int minY = bombY;
        int maxY = bombY;
        int centerX = bombX;
        int centerY = bombY;

        return new ExplosionFootprint(minX, minY, centerX, centerY, maxX, maxY);
    }

    private boolean isExplosionForbidden(Optional<GameFieldItem> gameFieldItemOptional) {
        return !gameFieldItemOptional.isPresent() || gameFieldItemOptional.get() == GameFieldItem.STONE;
    }
}
