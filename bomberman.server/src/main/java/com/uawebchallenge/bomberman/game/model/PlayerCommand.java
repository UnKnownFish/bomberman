package com.uawebchallenge.bomberman.game.model;

import java.util.Arrays;

public enum PlayerCommand {

    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    DOWN("down"),
    BOMB("bomb");

    private String command;

    PlayerCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

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
