package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.Coordinate;
import com.uawebchallenge.bomberman.game.model.field.GameField;

class ExplosionFootprintBuilder {

    public ExplosionFootprint build(int bombX, int bombY, int bombStrength, GameField gameField) {
        final Coordinate center = new Coordinate(bombX, bombY);
        final ExplosionBeam explosionBeam = new ExplosionBeam(center, gameField, bombStrength);
        int minX = explosionBeam.build(ExplosionBeam.Axis.X, ExplosionBeam.Direction.MIN);
        int maxX = explosionBeam.build(ExplosionBeam.Axis.X, ExplosionBeam.Direction.MAX);
        int minY = explosionBeam.build(ExplosionBeam.Axis.Y, ExplosionBeam.Direction.MIN);
        int maxY = explosionBeam.build(ExplosionBeam.Axis.Y, ExplosionBeam.Direction.MAX);

        return new ExplosionFootprint(minX, minY, bombX, bombY, maxX, maxY);
    }
}
