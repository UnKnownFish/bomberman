package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.Coordinate;
import lombok.Value;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Value
public class ExplosionFootprint {

    private final int minX;
    private final int minY;
    private final int centerX;
    private final int centerY;
    private final int maxX;
    private final int maxY;

    public ExplosionFootprint(int minX, int minY, int centerX, int centerY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.centerX = centerX;
        this.centerY = centerY;
        this.maxX = maxX;
        this.maxY = maxY;
        checkRep();
    }

    private void checkRep() {
        boolean correctState = minX <= centerX && centerX <= maxX && minY <= centerY && centerY <= maxY;
        if (!correctState) {
            throw new IllegalStateException("Wrong coordinates were provided for ExplosionFootprint. " + this.toString());
        }
    }

    public Collection<Coordinate> getCoordinates() {
        Stream<Coordinate> horizontalStream = IntStream.rangeClosed(minX, maxX).boxed().map(x -> new Coordinate(x, centerY));
        Stream<Coordinate> verticalStream = IntStream.rangeClosed(minY, maxY).boxed().map(y -> new Coordinate(centerX, y));

        return Stream.concat(horizontalStream, verticalStream).collect(Collectors.toSet());
    }
}
