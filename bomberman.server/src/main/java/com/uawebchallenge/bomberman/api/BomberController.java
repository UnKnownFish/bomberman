package com.uawebchallenge.bomberman.api;

import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
