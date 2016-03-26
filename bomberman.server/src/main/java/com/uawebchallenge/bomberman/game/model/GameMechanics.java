package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.model.player.Player;

import java.util.List;

public class GameMechanics {

    public void updateGameState(Game game) {
        List<Player> playerList = game.getPlayerList();

        playerList.stream().forEach(Player::updatePlayerState);
    }
}
