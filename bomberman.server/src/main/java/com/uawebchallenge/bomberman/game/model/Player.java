package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.utils.IdGenerator;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private final String playerId;
    private final PlayerType playerType;

    private PlayerCommand nextCommand;
    private PlayerCommand lastCommand;

    private double positionX;
    private double positionY;
    private double speed;

    private int timeBetweenFrames;
    private int bombTickDuration;
    private int bombExplosionDuration;

    private final List<Bomb> bombs = new LinkedList<>();

    public Player(GameConfig gameConfig, double initialPositionX, double initialPositionY, PlayerType playerType) {
        this.positionX = initialPositionX;
        this.positionY = initialPositionY;
        this.speed = gameConfig.getPlayerSpeed();
        this.timeBetweenFrames = gameConfig.getTimeBetweenFrames();
        this.bombTickDuration = gameConfig.getBombTickDuration();
        this.bombExplosionDuration = gameConfig.getBombExplosionDuration();
        this.playerType = playerType;
        this.playerId = IdGenerator.playerId();
    }
    public String getPlayerId() {
        return playerId;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setNextCommand(PlayerCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    public PlayerCommand getNextCommand() {
        return nextCommand;
    }

    public PlayerCommand getLastCommand() {
        return lastCommand;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void executeCommand() {
        double distance = this.timeBetweenFrames * this.speed;
        double x = this.positionX;
        double y = this.positionY;

        if (nextCommand == PlayerCommand.UP) {
            y = y - distance;
        } else if (nextCommand == PlayerCommand.DOWN) {
            y = y + distance;
        } else if (nextCommand == PlayerCommand.LEFT) {
            x = x - distance;
        } else if (nextCommand == PlayerCommand.RIGHT) {
            x = x + distance;
        } else if (nextCommand == PlayerCommand.BOMB) {
            Long bombX = Math.round(this.positionX);
            Long bombY = Math.round(this.positionY);
            Bomb bomb = new Bomb(bombX.intValue(), bombY.intValue(), this.bombTickDuration, this.bombExplosionDuration);
            bombs.add(bomb);
        }

        this.positionX = x;
        this.positionY = y;

        this.lastCommand = nextCommand;
        this.nextCommand = null;
    }
}
