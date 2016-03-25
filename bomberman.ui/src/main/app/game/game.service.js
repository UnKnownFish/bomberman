export default class GameService {

    constructor($http, $log) {
        "ngInject";
        this.http = $http;
        this.log = $log;
    }

    createNewGame() {
        return this.http.post("/bomberman/api/games", {});
    }
}