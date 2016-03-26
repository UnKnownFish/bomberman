export default class GameOverController {
    constructor($log, $location, gameService, gameConfig) {
        "ngInject";
        this.log = $log;
        this.location = $location;
        this.gameService = gameService;
        this.gameConfig = gameConfig;
        this.error = null;
    }

    startGame() {
        this.gameService.createNewGame()
            .then((response) => {
                this.log.info("New game was created successfully. Game details: " + JSON.stringify(response.data));
                const gameId = response.data.gameId;
                const playerId = response.data.playerId;
                this.gameConfig.setFieldHeight(response.data.fieldHeight);
                this.gameConfig.setFieldWidth(response.data.fieldWidth);

                const url = "/game/" + gameId + "/player/" + playerId;
                this.location.path(url);
            })
            .catch((response) => {
                this.log.error("Can't create new game. Error:");
                this.log.error(response);
                this.error = "Unfortunately connection to game server couldn't be established. " +
                    "Please contact yevgen.kravchenko@gmail.com"
            })
    }
}