package com.uawebchallenge.bomberman.game.model.bomb;

import com.uawebchallenge.bomberman.game.model.GameConfig;
import com.uawebchallenge.bomberman.game.model.field.GameField;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;

public class BombManager {

    public void placeBomb(Bomb bomb, GameField gameField, GameConfig gameConfig) {
        if (!bomb.isExploding()) {
            GameFieldItem bombFieldItem = getBombFieldItem(bomb.getProgress());
            gameField.setFieldItem(bomb.getPositionX(), bomb.getPositionY(), bombFieldItem);
        }
    }

    private static GameFieldItem getBombFieldItem(int bombProgress) {
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
}
