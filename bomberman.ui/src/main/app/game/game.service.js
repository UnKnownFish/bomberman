import SockJS from "sockjs-client";
import "stompjs/lib/stomp.js";

export default class GameService {

    constructor($http, $log) {
        "ngInject";
        this.http = $http;
        this.log = $log;
        this.wsClient = this.createWsClient();
    }

    createNewGame() {
        return this.http.post("/bomberman/api/games", {});
    }

    createWsClient() {
        const sockJS = new SockJS("/bomberman/ws");
        const client = Stomp.over(sockJS);
        client.debug = null;
        return client;
    }

    listenGameChange(gameId, callback) {
        const topicId = "/topic/" + gameId;
        this.wsClient.connect({}, (frame) => {
            this.log.debug("Connected to '/bomberman/ws' endpoint!");
            this.log.debug("Listeting to topic '" + topicId + "'");

            this.wsClient.subscribe(topicId, function(result){
                callback(JSON.parse(result.body));
            });
        });
    }
}