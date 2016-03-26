package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.model.bomb.Bomb;
import com.uawebchallenge.bomberman.game.model.bomb.BombManager;
import com.uawebchallenge.bomberman.game.model.player.Player;

import java.util.List;

public class GameMechanics {

    private final BombManager bombManager;

    public GameMechanics() {
        this.bombManager = new BombManager();
    }

    public void updateGameState(Game game) {
        List<Player> playerList = game.getPlayerList();

        playerList.stream().forEach(Player::updateState);

        playerList.stream()
                .flatMap(player -> player.getBombs().stream())
                .filter(Bomb::isActive)
                .forEach(bomb -> {
                    bomb.updateTimer();
                    bombManager.placeBomb(bomb, game.getGameField(), game.getGameConfig());
                });


    }
}
