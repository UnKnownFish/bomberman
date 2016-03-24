package com.uawebchallenge.bomberman.game.utils;

import com.uawebchallenge.bomberman.game.model.GameField;
import com.uawebchallenge.bomberman.game.model.GameFieldItem;

public class GameFieldGenerator {

    public static GameField generateGameField(int size) {
        // TODO Generate proper game field. so far I will hardcode it\

        GameFieldItem[][] fieldItems = new GameFieldItem[][]{
                {GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.EMPTY, GameFieldItem.BLOCK, GameFieldItem.STONE},
                {GameFieldItem.EMPTY, GameFieldItem.BLOCK, GameFieldItem.STONE, GameFieldItem.BLOCK, GameFieldItem.EMPTY},
                {GameFieldItem.EMPTY, GameFieldItem.BLOCK, GameFieldItem.BLOCK, GameFieldItem.BLOCK, GameFieldItem.BLOCK},
                {GameFieldItem.EMPTY, GameFieldItem.BLOCK, GameFieldItem.BLOCK, GameFieldItem.BLOCK, GameFieldItem.BLOCK},
                {GameFieldItem.EMPTY, GameFieldItem.BLOCK, GameFieldItem.BLOCK, GameFieldItem.BLOCK, GameFieldItem.BLOCK}
        };


        return new GameField(fieldItems);
    }
}
