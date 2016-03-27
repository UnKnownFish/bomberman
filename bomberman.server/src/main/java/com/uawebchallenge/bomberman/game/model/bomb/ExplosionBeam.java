package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.field.Coordinate;
import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExplosionBeam {

    private final Coordinate center;
    private final GameField gameField;
    private final int length;

    public ExplosionBeam(Coordinate center, GameField gameField, int length) {
        this.center = center;
        this.gameField = gameField;
        this.length = length;
    }

    public int build(Axis axis, Direction direction) {
        int value = axis == Axis.X ? center.getX() : center.getY();

        Iterator<Integer> iterator = getIterator(axis, direction);

        while (iterator.hasNext()) {
            value = iterator.next();

            int x = axis == Axis.X ? value : center.getX();
            int y = axis == Axis.Y ? value : center.getY();

            Optional<GameFieldItem> fieldItem = gameField.getFieldItem(x, y);

            if (isExplosionTarget(fieldItem)) {
                return value;
            }
            if (isExplosionForbidden(fieldItem)) {
                return direction == Direction.MAX ? value - 1 : value + 1;
            }
        }

        return value;
    }


    public enum Axis {
        X, Y
    }

    public enum Direction {
        MAX, MIN
    }

    private Iterator<Integer> getIterator(Axis axis, Direction direction) {
        int startRange = axis == Axis.X ? center.getX() : center.getY();
        int endRange = direction == Direction.MAX ? startRange + length : startRange - length;

        int min = Math.min(startRange, endRange);
        int max = Math.max(startRange, endRange);

        LinkedList<Integer> values = IntStream.rangeClosed(min, max)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));

        return direction == Direction.MAX ? values.iterator() : values.descendingIterator();
    }

    private boolean isExplosionTarget(Optional<GameFieldItem> gameFieldItemOptional) {
        return gameFieldItemOptional.isPresent() && gameFieldItemOptional.get() == GameFieldItem.BLOCK;
    }

    private boolean isExplosionForbidden(Optional<GameFieldItem> gameFieldItemOptional) {
        return !gameFieldItemOptional.isPresent() || gameFieldItemOptional.get() == GameFieldItem.STONE;
    }
}
