package com.uawebchallenge.bomberman.game.model.field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameFieldItem {

    EMPTY(0),
    BLOCK(1),
    STONE(2),
    BOMB_COLD(30),
    BOMB_WARM(31),
    BOMB_HOT(32),
    EXPLOSION(40),
    GRAVE(5);

    private int itemId;
}
