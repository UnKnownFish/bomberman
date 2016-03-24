package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameDataPush;
import com.uawebchallenge.bomberman.game.control.GameMechanics;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.Player;
import com.uawebchallenge.bomberman.game.utils.JsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultGameMechanics implements GameMechanics {

    private final GameDataPush gameDataPush;

    @Autowired
    public DefaultGameMechanics(GameDataPush gameDataPush) {
        this.gameDataPush = gameDataPush;
    }

    @Override
    public void updateGameState(Game game) {
        List<Player> playerList = game.getPlayerList();

        for(Player player : playerList) {
            player.executeCommand();
        }

        String gameData = JsonBuilder.toJson(game);
        gameDataPush.push(game.getGameId(), gameData);
    }
}
