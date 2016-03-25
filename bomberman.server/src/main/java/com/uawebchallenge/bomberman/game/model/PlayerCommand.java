package com.uawebchallenge.bomberman.game.model;

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
        for (PlayerCommand playerCommand : PlayerCommand.values()) {
            if (playerCommand.getCommand().equals(command)) {
                return playerCommand;
            }
        }
        throw new IllegalArgumentException(String.format("Provided string '%s' couldn't be transformed to PlayerCommand", command));
    }
}
