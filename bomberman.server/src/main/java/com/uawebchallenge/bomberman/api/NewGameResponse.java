package com.uawebchallenge.bomberman.api;

public class NewGameResponse {

    private final String gameId;
    private final String playerId;

    public NewGameResponse(String gameId, String playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
