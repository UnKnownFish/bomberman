package com.uawebchallenge.bomberman.game.model.player;

import com.uawebchallenge.bomberman.game.model.bomb.Bomb;
import com.uawebchallenge.bomberman.game.utils.IdGenerator;

import java.util.Collections;
import java.util.List;

public class Player {

    private final String playerId;
    private PlayerType playerType;

    private PlayerState nextPlayerState;
    private PlayerState currentPlayerState;

    public Player(double initialPositionX, double initialPositionY) {
        this.currentPlayerState = new PlayerState(initialPositionX, initialPositionY, Collections.<Bomb>emptyList());
        this.playerType = PlayerType.AI;
        this.playerId = IdGenerator.playerId();
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
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

    public void updateState() {
        if (nextPlayerState != null) {
            this.currentPlayerState = nextPlayerState;
            this.nextPlayerState = null;
        }
    }
}
