package com.uawebchallenge.bomberman.game;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Config {

    private final int time;

    private final double speed;
}
