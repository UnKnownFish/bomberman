package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.Coordinate;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class ExplosionFootprintTest {

    @Test
    public void getCoordinates() {
        ExplosionFootprint explosionFootprint = new ExplosionFootprint(1,1,4,3,5,4);
        Collection<Coordinate> coordinates = explosionFootprint.getCoordinates();
        assertEquals(8, coordinates.size());

        assertTrue(coordinates.contains(new Coordinate(1,3)));
        assertTrue(coordinates.contains(new Coordinate(2,3)));
        assertTrue(coordinates.contains(new Coordinate(3,3)));
        assertTrue(coordinates.contains(new Coordinate(4,3)));
        assertTrue(coordinates.contains(new Coordinate(5,3)));
        assertTrue(coordinates.contains(new Coordinate(4,1)));
        assertTrue(coordinates.contains(new Coordinate(4,2)));
        assertTrue(coordinates.contains(new Coordinate(4,4)));
    }
}