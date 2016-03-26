package com.uawebchallenge.bomberman.game.control.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uawebchallenge.bomberman.game.control.GameDataPush;
import com.uawebchallenge.bomberman.game.control.GameMechanics;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultGameMechanics implements GameMechanics {

    private final GameDataPush gameDataPush;

    private final static Logger logger = LoggerFactory.getLogger(DefaultGameMechanics.class);

    @Autowired
    public DefaultGameMechanics(GameDataPush gameDataPush) {
        this.gameDataPush = gameDataPush;
    }

    @Override
    public void updateGameState(Game game) {
        List<Player> playerList = game.getPlayerList();

        playerList.stream().forEach(Player::updatePlayerState);

        push(game);
    }

    private void push(Game game) {
        try {
            String gameData = JsonBuilder.toJson(game);
            gameDataPush.push(game.getGameId(), gameData);
        } catch (JsonProcessingException e) {
            logger.error("Error parsing game object to JSON.", e);
        }
    }
}
