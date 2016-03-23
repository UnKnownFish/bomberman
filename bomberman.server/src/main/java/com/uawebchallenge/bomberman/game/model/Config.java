package com.uawebchallenge.bomberman.game.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Config {

    public final static Config DEFAULT =
            Config.builder()
                    .speed(0.004)
                    .time(50)
                    .build();


    private final int time;

    private final double speed;
}
