package com.uawebchallenge.bomberman.game.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum PlayerCommand {

    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    DOWN("down"),
    BOMB("bomb");

    private String command;

    public static PlayerCommand getCommand(String command) {
        PlayerCommand playerCommand = Arrays.stream(PlayerCommand.values())
                .filter(c -> c.getCommand().equals(command))
                .findFirst().get();

        if (playerCommand == null) {
            throw new IllegalArgumentException(String.format("Provided string '%s' couldn't be transformed to PlayerCommand", command));
        }
        return playerCommand;
    }
}
