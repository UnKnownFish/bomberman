package com.uawebchallenge.bomberman.api;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class CommandWsService {

    private final GameManager gameManager;

    @Autowired
    public CommandWsService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @MessageMapping("/game/{gameId}/player/{playerId}/command")
    public void captureCommand(@DestinationVariable String gameId,
                               @DestinationVariable String playerId,
                               AddCommandRequest commandRequest) {
        String command = commandRequest.getCommand();

        PlayerCommand playerCommand = PlayerCommand.getCommand(command);
        Game game = gameManager.getGame(gameId);
        Player player = game.getPlayer(playerId);
        PlayerState newPlayerState = PlayerStateEbator.execute(playerCommand, player.getCurrentPlayerState(), game.getGameField(), game.getGameConfig());
        player.setNextPlayerState(newPlayerState);
    }
}
