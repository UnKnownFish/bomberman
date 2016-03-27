import clipboard from "clipboard-js";

export default class GameController {
    constructor($log, $stateParams, $scope, $document, $location, $timeout, gameConfig, gameService, gameWsClient) {
        "ngInject";
        this.log = $log;
        this.scope = $scope;
        this.document = $document;
        this.location = $location;
        this.timeout = $timeout;

        this.gameService = gameService;
        this.gameWsClient = gameWsClient;

        this.model = {
            gameId: $stateParams.gameId,
            playerId: $stateParams.playerId,
            fieldWidth: gameConfig.getFieldWidth(),
            fieldHeight: gameConfig.getFieldHeight(),
            gameField: null,
            players: null,
            maxBombs: 1,
            humanPlayers: null
        };

        this.listenKeyPress();
        this.listenGameChange();
    }

    listenGameChange() {
        this.gameWsClient.listen(this.model.gameId, (data) => {
            this.model.gameField = data.field;
            this.model.players = data.players;
            this.model.maxBombs = data.maxBombs;
            this.model.humanPlayers = data.humanPlayers;
            this.scope.$digest();

            if (data.gameOver || this.isPlayerDead()) {
                this.gameOver();
            }
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
            case 30:
                return "item bomb_cold";
            case 31:
                return "item bomb_warm";
            case 32:
                return "item bomb_hot";
            case 40:
                return "item bomb_explosion";

            default:
                return null;
        }
    }

    playerPositionStyle(playerIndex) {
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

    playerClass(playerIndex) {
        if (this.model.players) {
            const player = this.model.players[playerIndex];
            const myplayer = player.id == this.model.playerId ? " myplayer" : "";
            const playerClass = "player" + (playerIndex + 1) + myplayer;
            return (!player.dead) ? playerClass : "grave";
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
        this.gameWsClient.sendCommand(this.model.gameId, this.model.playerId, command);
        //this.gameService.sendCommand(this.model.gameId, this.model.playerId, command);
    }

    gameOver() {
        this.timeout(() => {
            this.location.path("/gameover");
        }, 2500);
    }

    bombIndicatorWidthStyle() {
        var width = "width:" + (this.model.maxBombs * 32) + "px";
        return width;
    }

    isPlayerDead() {
        const player = this.model.players.filter((p) => {
            return p.id == this.model.playerId
        })[0];
        return player.dead;
    }

    copyGameId() {
        clipboard.copy(this.model.gameId);
    }
}