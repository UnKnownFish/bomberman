
export default class GameController {
    constructor($log, $scope, gameService, gameWsClient) {
        "ngInject";
        this.log = $log;
        this.scope = $scope;
        this.gameService = gameService;
        this.gameWsClient = gameWsClient;
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
                this.log.info("New game was created successfully. Game details: " + JSON.stringify(response.data));
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
        this.gameWsClient.listen(this.model.gameId, (data) => {
            this.model.gameField = data.field;
            console.log(JSON.stringify(data.field));
            this.scope.$digest();
        });
    }
}