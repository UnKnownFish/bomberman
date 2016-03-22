package com.uawebchallenge.bomberman.game;

import java.util.LinkedList;

public class Player {

    private final LinkedList<PlayerCommand> commandsLog = new LinkedList<PlayerCommand>();

    // TODO Store current player position

    public synchronized void addCommand(PlayerCommand playerCommand) {
        commandsLog.add(playerCommand);
    }

    public PlayerCommand getLatestCommand() {
        return commandsLog.getLast();
    }

    public synchronized void clearCommandsLog() {
        commandsLog.clear();
    }
}
