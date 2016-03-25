package com.uawebchallenge.bomberman.game.model;


public class PlayerStateEbator {

    public static PlayerState execute(PlayerCommand playerCommand, PlayerState oldPlayerState, GameField gameField, GameConfig gameConfig) {
        PlayerState newPlayerState = PlayerCommandExecutor.execute(playerCommand, oldPlayerState, gameConfig);
        boolean validState = PlayerStateValidator.isStateValid(newPlayerState, gameField, gameConfig);
        return validState ? newPlayerState : oldPlayerState;
    }
}
