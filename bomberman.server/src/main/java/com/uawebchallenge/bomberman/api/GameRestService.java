package com.uawebchallenge.bomberman.api;

import com.uawebchallenge.bomberman.game.control.GameService;
import com.uawebchallenge.bomberman.game.control.NewGameData;
import com.uawebchallenge.bomberman.game.exception.BombermanException;
import com.uawebchallenge.bomberman.game.model.player.PlayerCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class GameRestService {

    private final GameService gameService;

    private final Logger logger = LoggerFactory.getLogger(GameRestService.class);

    @Autowired
    public GameRestService(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public NewGameData createNewGame() throws BombermanException {
        return gameService.createNewGame();
    }

    @RequestMapping(path = "/games/{gameId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public NewGameData connectToGame(@PathVariable String gameId) throws BombermanException {
        return gameService.connectToGame(gameId);
    }

    @RequestMapping(path = "/game/{gameId}/player/{playerId}/command", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addCommand(@PathVariable String gameId,
                           @PathVariable String playerId,
                           @RequestBody CommandRequest request) throws BombermanException {
        PlayerCommand playerCommand = PlayerCommand.getCommand(request.getCommand());
        gameService.addCommand(gameId, playerId, playerCommand);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BombermanException.class)
    public Error handleUserNotFound(BombermanException e) {
        String msg = e.getMessage();
        logger.warn(msg);
        return new Error(msg);
    }
}
