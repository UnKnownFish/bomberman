package com.uawebchallenge.bomberman.game.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum PlayerCommand {

    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    DOWN("down"),
    BOMB("bomb");

    private String command;

    public static Optional<PlayerCommand> getCommand(String command) {
        return Arrays.stream(PlayerCommand.values())
                .filter(c -> c.getCommand().equals(command))
                .findFirst();
    }
}
