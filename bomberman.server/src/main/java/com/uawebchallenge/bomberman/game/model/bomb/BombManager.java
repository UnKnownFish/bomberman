package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;

import java.util.List;
import java.util.stream.Collectors;

public class BombManager {

    private final ExplosionFootprintBuilder explosionFootprintBuilder = new ExplosionFootprintBuilder();

    public void placeBomb(Bomb bomb, GameField gameField, GameConfig gameConfig) {
        if (!bomb.isExploding()) {
            GameFieldItem bombFieldItem = getBombFieldItem(bomb.getProgress());
            gameField.setFieldItem(bomb.getPositionX(), bomb.getPositionY(), bombFieldItem);
        } else {
            ExplosionFootprint explosionFootprint = getExplosionFootprint(bomb, gameField, gameConfig);

            if (bomb.isActive()) {
                gameField.setFieldItems(explosionFootprint.getCoordinates(), GameFieldItem.EXPLOSION);
            } else {
                gameField.setFieldItems(explosionFootprint.getCoordinates(), GameFieldItem.EMPTY);
            }
        }
    }

    public void clearBombs(List<Bomb> playerBombs) {
        if (playerBombs != null && playerBombs.size() > 0) {
            List<Bomb> activeBombs = playerBombs.stream().filter(Bomb::isActive).collect(Collectors.toList());
            playerBombs.clear();
            playerBombs.addAll(activeBombs);
        }
    }

    private GameFieldItem getBombFieldItem(int bombProgress) {
        if (bombProgress < 30) {
            return GameFieldItem.BOMB_COLD;
        } else if (bombProgress < 60) {
            return GameFieldItem.BOMB_WARM;
        } else if (bombProgress < 90) {
            return GameFieldItem.BOMB_HOT;
        } else {
            return GameFieldItem.EXPLOSION;
        }
    }

    private ExplosionFootprint getExplosionFootprint(Bomb bomb, GameField gameField, GameConfig gameConfig) {
        ExplosionFootprint explosionFootprint = bomb.getExplosionFootprint();

        if (explosionFootprint == null) {
            explosionFootprint = explosionFootprintBuilder.build(bomb.getPositionX(), bomb.getPositionY(), gameConfig.getBombStrength(), gameField);
            bomb.setExplosionFootprint(explosionFootprint);
        }

        return explosionFootprint;
    }
}
