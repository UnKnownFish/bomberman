package com.uawebchallenge.bomberman.game;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private PlayerCommand command;
    private PlayerCommand lastCommand;

    private double positionX;
    private double positionY;
    private double speed;

    private int bombTickDuration;
    private int bombExplosionDuration;

    private final List<Bomb> bombs = new LinkedList<Bomb>();

    public Player(double positionX, double positionY, double playerSpeed, int bombTickDuration, int bombExplosionDuration) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = playerSpeed;
        this.bombTickDuration = bombTickDuration;
        this.bombExplosionDuration = bombExplosionDuration;
    }

    public void setNextCommand(PlayerCommand nextCommand) {
        this.command = nextCommand;
    }

    public PlayerCommand getLastCommand() {
        return lastCommand;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void executeCommand(int time) {
        double distance = time * this.speed;
        double x = this.positionX;
        double y = this.positionY;

        if (command == PlayerCommand.UP) {
            y = y - distance;
        } else if (command == PlayerCommand.DOWN) {
            y = y + distance;
        } else if (command == PlayerCommand.LEFT) {
            x = x - distance;
        } else if (command == PlayerCommand.RIGHT) {
            x = x + distance;
        } else if (command == PlayerCommand.BOMB) {
            Long bombX = Math.round(this.positionX);
            Long bombY = Math.round(this.positionY);
            Bomb bomb = new Bomb(bombX.intValue(), bombY.intValue(), this.bombTickDuration, this.bombExplosionDuration);
            bombs.add(bomb);
        }

        this.positionX = x;
        this.positionY = y;

        this.lastCommand = command;
        this.command = null;
    }
}
