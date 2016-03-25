package com.uawebchallenge.bomberman.game.model;

import lombok.Value;

import java.util.List;

@Value
public class PlayerState {
    private double positionX;
    private double positionY;
    private final List<Bomb> bombs;
}
