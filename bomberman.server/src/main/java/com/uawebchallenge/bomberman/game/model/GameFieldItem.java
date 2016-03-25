package com.uawebchallenge.bomberman.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameFieldItem {

    EMPTY(0),
    BLOCK(1),
    STONE(2),
    BOMB_TICK1(30),
    BOMB_TICK2(31),
    BOMB_TICK3(32),
    EXPLOSION_TICK1(40),
    EXPLOSION_TICK2(41),
    GRAVE(5);

    private int itemId;
}
