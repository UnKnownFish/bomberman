package com.uawebchallenge.bomberman.game.model;

public class GameField {

    private GameFieldItem[][] fieldItems;

    public GameField(GameFieldItem[][] fieldItems) {
        this.fieldItems = fieldItems;
    }

    public GameFieldItem[][] getFieldItems() {
        return fieldItems;
    }

    public GameFieldItem getFieldItem(int x, int y) {
        // TODO Protect from index overflow
        return fieldItems[y][x];
    }
}
