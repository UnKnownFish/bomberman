package com.uawebchallenge.bomberman.game.model;

import com.uawebchallenge.bomberman.game.model.bomb.Bomb;
import com.uawebchallenge.bomberman.game.model.bomb.BombManager;
import com.uawebchallenge.bomberman.game.model.field.Coordinate;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;
import com.uawebchallenge.bomberman.game.model.player.Player;
import com.uawebchallenge.bomberman.game.model.player.PlayerType;

import java.util.List;
import java.util.Optional;

public class GameMechanics {

    private final BombManager bombManager;

    public GameMechanics() {
        this.bombManager = new BombManager();
    }

    public void updateGameState(Game game) {
        List<Player> playerList = game.getPlayerList();

        // Update all bombs that are currently located or should be placed on the game field
        playerList.stream()
                .flatMap(player -> player.getBombs().stream())
                .filter(Bomb::isActive)
                .forEach(bomb -> {
                    bomb.updateTimer();
                    bombManager.placeBomb(bomb, game.getGameField(), game.getGameConfig());
                });

        // Update player state and player related data
        playerList.stream().forEach(player -> {
            player.updateState();

            Coordinate gridCoordinate = player.getGridCoordinate();
            Optional<GameFieldItem> fieldItem = game.getGameField().getFieldItem(gridCoordinate.getX(), gridCoordinate.getY());
            if (!fieldItem.isPresent() || fieldItem.get() == GameFieldItem.EXPLOSION) {
                player.die();
            }
            bombManager.clearBombs(player.getBombs());
        });

        // Update game state. Check if its not over yet
        long activeHumanPlayers = playerList.stream()
                .filter(p ->
                        p.getPlayerType() == PlayerType.HUMAN && !p.isDead() && p.getIdleIterationsCount() < game.getGameConfig().getIdleIterationsThreshold())
                .count();

        if (activeHumanPlayers == 0) {
            game.setOver();
        }

    }
}
