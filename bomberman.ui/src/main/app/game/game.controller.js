export default class GameController {
    constructor($log, $scope, $document, gameService, gameWsClient) {
        "ngInject";
        this.log = $log;
        this.scope = $scope;
        this.document = $document;

        this.gameService = gameService;
        this.gameWsClient = gameWsClient;

        this.model = {
            gameId: null,
            playerId: null,
            gameField: null,
            fieldWidth: 0,
            fieldHeight: 0,
            players: null
        };

        this.listenKeyPress();
        this.createNewGame();
    }

    createNewGame() {
        this.gameService.createNewGame()
            .then((response) => {
                this.log.info("New game was created successfully. Game details: " + JSON.stringify(response.data));
                this.model.gameId = response.data.gameId;
                this.model.playerId = response.data.playerId;
                this.model.fieldHeight = response.data.fieldHeight;
                this.model.fieldWidth = response.data.fieldWidth;
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
            this.model.players = data.players;
            //console.log(JSON.stringify(data));
            this.scope.$digest();
        });
    }

    rangeTo(max) {
        const min = 0;
        let input = [];
        for (var i = min; i < max; i++) {
            input.push(i);
        }
        return input;
    };

    fieldClass(i, j) {
        const fieldItemId = (this.model.gameField != null) ? this.model.gameField[i][j] : null;

        switch (fieldItemId) {
            case 0:
                return "item empty";
            case 1:
                return "item block";
            case 2:
                return "item stone";
            default:
                return null;
        }
    }

    playerStyle(playerIndex) {
        if (this.model.players) {
            const player = this.model.players[playerIndex];
            const x = player.x;
            const y = player.y;

            const positionX = 32 * x;
            const positionY = 32 * y;
            return "transform: translate3d(" + positionX + "px," + positionY + "px, 0)";
        }
        return null;
    }


    listenKeyPress() {
        this.document.bind("keydown", (event) => {
            switch (event.keyCode) {
                case 37:
                    this.executeCommand("left");
                    break;
                case 38:
                    this.executeCommand("up");
                    break;
                case 39:
                    this.executeCommand("right");
                    break;
                case 40:
                    this.executeCommand("down");
                    break;
                case 32:
                    this.executeCommand("bomb");
                    break;
            }
        })
    }

    executeCommand(command) {
        //this.gameWsClient.sendCommand(this.model.gameId, this.model.playerId, command);
        this.gameService.sendCommand(this.model.gameId, this.model.playerId, command);
    }
}