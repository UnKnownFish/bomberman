package com.uawebchallenge.bomberman.game.control;

import lombok.Value;

@Value
public class NewGameData {
    private final String gameId;
    private final String playerId;
    private final int fieldWidth;
    private final int fieldHeight;
}
