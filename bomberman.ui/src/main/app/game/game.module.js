import "angular/angular.js";
import router from "angular-ui-router";
import routes from "./game.route.js";
import GameController from "./game.controller.js";
import GameConfig from "./game.config.js";
import GameService from "./game.service.js";
import GameWsClient from "./game.ws.client.js";
import digest from "./../digest/digest.module.js";

import "./../resources/style.css";

let gameModule = angular.module("game", [digest])
    .controller("GameController", GameController)
    .service("gameService", GameService)
    .service("gameConfig", GameConfig)
    .service("gameWsClient", GameWsClient)
    .config(routes);

export default gameModule.name;