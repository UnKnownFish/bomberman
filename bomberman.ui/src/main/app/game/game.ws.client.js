import SockJS from "sockjs-client";
import "stompjs/lib/stomp.js";

export default class GameWsClient {

    constructor($log, $q) {
        "ngInject";
        this.log = $log;
        this.q = $q;
        this.client = this.createClient();
        this.connectionPromise = this.connect();
    }

    createClient() {
        const sockJS = new SockJS("/bomberman/ws");
        let client = Stomp.over(sockJS);
        client.debug = null;
        return client;
    }

    connect() {
        let deferred = this.q.defer();

        const headers = {};
        const successCallback = () => {
            this.log.info("Connected to '/bomberman/ws' endpoint. Gratz!")
            deferred.resolve();
        };
        const failureCallback = () => {
            this.log.error("Oops. Failed to connected to '/bomberman/ws' endpoint.")
            deferred.reject();
        };

        this.client.connect(headers, successCallback, failureCallback);

        return deferred.promise;
    }

    listen(gameId, callback) {
        const topicId = "/topic/" + gameId;

        this.connectionPromise.then(
            ()=> {
                this.client.subscribe(topicId, function (result) {
                    callback(JSON.parse(JSON.parse(result.body)));
                });
            });
    }
}