package com.uawebchallenge.bomberman.api;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BomberController {

    private final GameManager gameManager;

    @Autowired
    public BomberController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public NewGameResponse createNewGame() {
        Game newGame = gameManager.createNewGame();
        GameConfig gameConfig = newGame.getGameConfig();
        Player player = newGame.connectHuman();
        gameManager.startGame(newGame);

        return new NewGameResponse(newGame.getGameId(), player.getPlayerId(), gameConfig.getFieldWidth(), gameConfig.getFieldHeight());
    }

    @RequestMapping(path = "/game/{gameId}/player/{playerId}/command", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addCommand(@PathVariable String gameId,
                           @PathVariable String playerId,
                           @RequestBody AddCommandRequest request) {
        PlayerCommand playerCommand = PlayerCommand.getCommand(request.getCommand());
        Game game = gameManager.getGame(gameId);
        Player player = game.getPlayer(playerId);
        PlayerState newPlayerState = PlayerStateEbator.execute(playerCommand, player.getCurrentPlayerState(), game.getGameField(), game.getGameConfig());
        player.setNextPlayerState(newPlayerState);
    }
}
