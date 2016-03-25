package com.uawebchallenge.bomberman.game.utils;

import com.uawebchallenge.bomberman.game.model.GameField;
import com.uawebchallenge.bomberman.game.model.GameFieldItem;

import java.util.concurrent.ThreadLocalRandom;

public class GameFieldGenerator {

    public static GameField generateGameField(int width, int height) {
        GameFieldItem[][] fieldItems = new GameFieldItem[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (isPlayerIsland(row, col, width, height)) {
                    fieldItems[row][col] = GameFieldItem.EMPTY;
                } else if (isEvenRowColumn(row, col)) {
                    fieldItems[row][col] = GameFieldItem.STONE;
                } else if (ThreadLocalRandom.current().nextInt(0, 100) < 70) {
                    fieldItems[row][col] = GameFieldItem.BLOCK;
                } else {
                    fieldItems[row][col] = GameFieldItem.EMPTY;
                }
            }
        }
        return new GameField(fieldItems);
    }

    private static boolean isPlayerIsland(int row, int col, int width, int height) {
        final boolean edgeCol = col == 0 || col == width - 1;
        final boolean edgeRow = row == 0 || row == height - 1;
        final boolean edgeNeighborCol = col == 1 || col == width - 2;
        final boolean edgeNeighborRow = row == 1 || row == height - 2;

        return (edgeCol && edgeRow) || ((edgeCol && edgeNeighborRow) || (edgeNeighborCol && edgeRow));
    }

    private static boolean isEvenRowColumn(int row, int col) {
        return (col + 1) % 2 == 0 && (row + 1) % 2 == 0;
    }
}
