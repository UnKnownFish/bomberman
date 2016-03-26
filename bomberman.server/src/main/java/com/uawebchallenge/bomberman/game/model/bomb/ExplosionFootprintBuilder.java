package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.GameField;

class ExplosionFootprintBuilder {

    public ExplosionFootprint build(int bombX, int bombY, int bombStrength, GameField gameField) {
        // TODO implement me
        return new ExplosionFootprint(bombX, bombY, bombX, bombY, bombX, bombY);
    }
}
