package com.uawebchallenge.bomberman.game.model.player;

import com.uawebchallenge.bomberman.game.model.bomb.Bomb;
import com.uawebchallenge.bomberman.game.model.field.Coordinate;
import com.uawebchallenge.bomberman.game.utils.IdGenerator;

import java.util.Collections;
import java.util.List;

public class Player {

    private final String playerId;
    private PlayerType playerType;

    private PlayerState nextPlayerState;
    private PlayerState currentPlayerState;

    private boolean dead;
    private long idleIterationsCount;

    public Player(double initialPositionX, double initialPositionY) {
        this.currentPlayerState = new PlayerState(initialPositionX, initialPositionY, Collections.<Bomb>emptyList());
        this.playerType = PlayerType.AI;
        this.playerId = IdGenerator.playerId();
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setHuman() {
        this.playerType = PlayerType.HUMAN;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public double getPositionX() {
        return currentPlayerState.getPositionX();
    }

    public double getPositionY() {
        return currentPlayerState.getPositionY();
    }

    public void setNextPlayerState(PlayerState nextPlayerState) {
        this.nextPlayerState = nextPlayerState;
    }

    public PlayerState getCurrentPlayerState() {
        return currentPlayerState;
    }

    public List<Bomb> getBombs() {
        if (currentPlayerState != null && currentPlayerState.getBombs() != null) {
            return currentPlayerState.getBombs();
        }
        return Collections.emptyList();
    }

    public Coordinate getGridCoordinate() {
        int x = (int) Math.round(getPositionX());
        int y = (int) Math.round(getPositionY());
        return new Coordinate(x, y);
    }

    public void updateState() {
        updateIdleIterationsCount();

        if (nextPlayerState != null) {
            this.currentPlayerState = nextPlayerState;
            this.nextPlayerState = null;
        }
    }

    private void updateIdleIterationsCount() {
        boolean idleIteration = nextPlayerState == null || nextPlayerState.equals(currentPlayerState);
        this.idleIterationsCount = idleIteration ? idleIterationsCount + 1 : 0;
    }

    public void die() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public long getIdleIterationsCount() {
        return idleIterationsCount;
    }
}
