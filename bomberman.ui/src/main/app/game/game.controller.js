
export default class GameController {
    constructor($log, gameService) {
        "ngInject";
        this.log = $log;
        this.gameService = gameService;
        this.model = {
            gameId: null,
            playerId: null,
            gameField: null
        };

        this.createNewGame();
    }

    createNewGame() {
        this.gameService.createNewGame()
            .then((response) => {
                this.model.gameId = response.data.gameId;
                this.model.playerId = response.data.playerId;
                this.listenGameChange();
            })
            .catch((error) => {
                this.log.error("Can't create new game. Error:");
                this.log.error(error);
            })
    }

    listenGameChange() {
        this.gameService.listenGameChange(this.model.gameId, (data) => {
            this.log.info(new Date());
        });
    }


}