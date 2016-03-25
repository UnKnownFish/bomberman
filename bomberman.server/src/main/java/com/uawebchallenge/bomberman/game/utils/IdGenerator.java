package com.uawebchallenge.bomberman.game.utils;

import java.util.UUID;

public class IdGenerator {

    public static String gameId() {
        return UUID.randomUUID().toString();
    }

    public static String playerId() {
        return UUID.randomUUID().toString();
    }
}
