package com.uawebchallenge.bomberman.game.model.player;

import com.uawebchallenge.bomberman.game.model.bomb.Bomb;
import com.uawebchallenge.bomberman.game.model.GameConfig;

import java.util.LinkedList;
import java.util.List;

class PlayerStateChanger {

    PlayerState change(PlayerCommand playerCommand, PlayerState oldPlayerState, GameConfig gameConfig) {
        double distance = gameConfig.getTimeBetweenFrames() * gameConfig.getPlayerSpeed();

        double x = oldPlayerState.getPositionX();
        double y = oldPlayerState.getPositionY();
        List<Bomb> bombs = new LinkedList<>(oldPlayerState.getBombs());

        if (playerCommand == PlayerCommand.UP) {
            y = y - distance;
            x = Math.round(x);
        } else if (playerCommand == PlayerCommand.DOWN) {
            y = y + distance;
            x = Math.round(x);
        } else if (playerCommand == PlayerCommand.LEFT) {
            x = x - distance;
            y = Math.round(y);
        } else if (playerCommand == PlayerCommand.RIGHT) {
            x = x + distance;
            y = Math.round(y);
        } else if (playerCommand == PlayerCommand.BOMB) {
            Long bombX = Math.round(x);
            Long bombY = Math.round(y);
            Bomb bomb = new Bomb(bombX.intValue(), bombY.intValue(), gameConfig.getBombTickDuration(), gameConfig.getBombExplosionDuration());
            bombs.add(bomb);
        }

        return new PlayerState(x, y, bombs);
    }
}
