package com.uawebchallenge.bomberman.api;

import com.uawebchallenge.bomberman.game.control.GameService;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CommandWsService {

    private final GameService gameService;

    @Autowired
    public CommandWsService(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/game/{gameId}/player/{playerId}/command")
    public void captureCommand(@DestinationVariable String gameId,
                               @DestinationVariable String playerId,
                               CommandRequest commandRequest) {
        String command = commandRequest.getCommand();
        PlayerCommand playerCommand = PlayerCommand.getCommand(command);
        gameService.addCommand(gameId, playerId, playerCommand);
    }
}
