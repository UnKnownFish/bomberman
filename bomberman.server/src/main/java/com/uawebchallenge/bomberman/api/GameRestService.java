package com.uawebchallenge.bomberman.api;

import com.uawebchallenge.bomberman.game.control.GameService;
import com.uawebchallenge.bomberman.game.control.NewGameData;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class GameRestService {

    private final GameService gameService;

    @Autowired
    public GameRestService(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public NewGameData createNewGame() {
        return gameService.createNewGame();
    }

    @RequestMapping(path = "/game/{gameId}/player/{playerId}/command", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addCommand(@PathVariable String gameId,
                           @PathVariable String playerId,
                           @RequestBody CommandRequest request) {
        PlayerCommand playerCommand = PlayerCommand.getCommand(request.getCommand());
        gameService.addCommand(gameId, playerId, playerCommand);
    }
}
