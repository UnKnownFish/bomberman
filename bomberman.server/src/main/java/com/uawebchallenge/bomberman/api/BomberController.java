package com.uawebchallenge.bomberman.api;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.Player;
import com.uawebchallenge.bomberman.game.model.PlayerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
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
        Player player = newGame.connectHuman();
        gameManager.startGame(newGame);
        return new NewGameResponse(newGame.getGameId(), player.getPlayerId());
    }

    @RequestMapping(path = "/game/{gameId}/player/{playerId}/command", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addCommand(@PathVariable String gameId,
                           @PathVariable String playerId,
                           @RequestBody AddCommandRequest request) {
        PlayerCommand playerCommand = PlayerCommand.getCommand(request.getCommand());
        Game game = gameManager.getGame(gameId);
        Player player = game.getPlayer(playerId);
        player.setNextCommand(playerCommand);
    }
}
