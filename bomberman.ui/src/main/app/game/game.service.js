export default class GameService {

    constructor($http, $log) {
        "ngInject";
        this.http = $http;
        this.log = $log;
    }

    createNewGame() {
        return this.http.post("/bomberman/api/games", {});
    }

    sendCommand(gameId, playerId, command) {
        const url = "/bomberman/api/game/" + gameId + "/player/" + playerId + "/command";
        return this.http.post(url, {
            command: command
        });
    }
}