package com.uawebchallenge.bomberman.game.exception;

public class BombermanException extends Exception {

    private BombermanException(String message) {
        super(message);
    }

    public static BombermanException commandNotValid(String command) {
        return new BombermanException("Command is not valid. Command:" + command);
    }

    public static BombermanException gameIsOver(String gameId) {
        return new BombermanException("Game is over. GameId:" + gameId);
    }

    public static BombermanException gameNotFound(String gameId) {
        return new BombermanException("Couldn't find running game. GameId:" + gameId);
    }

    public static BombermanException playerNotConnected(String gameId) {
        return new BombermanException("Couldn't connect player. GameId:" + gameId);
    }

    public static BombermanException playerNotFound(String gameId, String playerId) {
        return new BombermanException("Player was not found. GameId:" + gameId +
                ". PlayerId:" + playerId);
    }
}
