package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExplosionFootprintBuilderTest {

    private final ExplosionFootprintBuilder explosionFootprintBuilder = new ExplosionFootprintBuilder();


    @Test
    public void build() {
        final GameField gameField = new GameField(
                new GameFieldItem[][]{
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY}
                }
        );

        ExplosionFootprint explosionFootprint = explosionFootprintBuilder.build(3, 3, 2, gameField);
        assertNotNull(explosionFootprint);

        ExplosionFootprint expectedExplosionFootprint = new ExplosionFootprint(1, 1, 3, 3, 5, 5);
        assertEquals(expectedExplosionFootprint, explosionFootprint);
    }

    @Test
    public void buildWithObstacles() {
        final GameField gameField = new GameField(
                new GameFieldItem[][]{
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.STONE, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.STONE, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.BLOCK, GameFieldItem.STONE, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.STONE, GameFieldItem.EMPTY},
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.STONE, GameFieldItem.EMPTY},
                        {GameFieldItem.STONE, GameFieldItem.STONE, GameFieldItem.STONE, GameFieldItem.STONE, GameFieldItem.STONE, GameFieldItem.STONE}
                }
        );

        ExplosionFootprint explosionFootprint = explosionFootprintBuilder.build(3, 3, 2, gameField);
        assertNotNull(explosionFootprint);

        ExplosionFootprint expectedExplosionFootprint = new ExplosionFootprint(1, 2, 3, 3, 3, 4);
        assertEquals(expectedExplosionFootprint, explosionFootprint);
    }

    @Test
    public void buildNearBorder() {
        final GameField gameField = new GameField(
                new GameFieldItem[][]{
                        {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.BLOCK},
                        {GameFieldItem.EMPTY, GameFieldItem.STONE, GameFieldItem.BLOCK},
                        {GameFieldItem.BLOCK, GameFieldItem.EMPTY, GameFieldItem.BLOCK},
                        {GameFieldItem.BLOCK, GameFieldItem.STONE, GameFieldItem.BLOCK},
                }
        );

        ExplosionFootprint explosionFootprint = explosionFootprintBuilder.build(1, 0, 2, gameField);
        assertNotNull(explosionFootprint);

        ExplosionFootprint expectedExplosionFootprint = new ExplosionFootprint(0, 0, 1, 0, 2, 0);
        assertEquals(expectedExplosionFootprint, explosionFootprint);
    }
}