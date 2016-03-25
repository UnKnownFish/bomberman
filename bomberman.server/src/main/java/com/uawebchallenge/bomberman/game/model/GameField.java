package com.uawebchallenge.bomberman.game.model;

import java.util.Optional;

public class GameField {

    private GameFieldItem[][] fieldItems;

    public GameField(GameFieldItem[][] fieldItems) {
        this.fieldItems = fieldItems;
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
}
