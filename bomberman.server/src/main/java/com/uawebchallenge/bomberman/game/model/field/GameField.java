package com.uawebchallenge.bomberman.game.model.field;

import java.util.Collection;
import java.util.Optional;

public class GameField {

    private final GameFieldItem[][] fieldItems;

    public GameField(int width, int height) {
        this.fieldItems = GameFieldGenerator.generateGameField(width, height);
    }

    public GameFieldItem[][] getFieldItems() {
        return fieldItems;
    }

    public Optional<GameFieldItem> getFieldItem(int x, int y) {
        if (y < 0 || y >= fieldItems.length || x < 0 || x >= fieldItems[y].length) {
            return Optional.empty();
        }
        return Optional.of(fieldItems[y][x]);
    }

    public void setFieldItem(int x, int y, GameFieldItem fieldItem) {
        if (y >= 0 && y < fieldItems.length && x >= 0 || x < fieldItems[y].length) {
            fieldItems[y][x] = fieldItem;
        }
    }

    public void setFieldItems(Collection<Coordinate> coordinates, GameFieldItem fieldItem) {
        if (coordinates != null) {
            coordinates.stream().forEach(c -> setFieldItem(c.getX(), c.getY(), fieldItem));
        }
    }
}
