package com.uawebchallenge.bomberman.game.model.player;


import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.field.GameField;

public class PlayerCommandExecutor {

    private final PlayerStateChanger playerStateChanger;
    private final PlayerStateValidator playerStateValidator;

    public PlayerCommandExecutor() {
        playerStateValidator = new PlayerStateValidator();
        playerStateChanger = new PlayerStateChanger();
    }

    public PlayerState execute(PlayerCommand playerCommand, PlayerState oldPlayerState, GameField gameField, GameConfig gameConfig) {
        PlayerState newPlayerState = playerStateChanger.change(playerCommand, oldPlayerState, gameConfig);
        boolean validState = playerStateValidator.isStateValid(playerCommand, newPlayerState, gameField, gameConfig);
        return validState ? newPlayerState : oldPlayerState;
    }
}
