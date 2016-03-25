import "angular/angular.js";
import router from "angular-ui-router";
import routes from "./game.route.js";
import GameController from "./game.controller.js";
import GameService from "./game.service.js";

let gameModule = angular.module("game", [])
    .controller("GameController", GameController)
    .service("gameService", GameService)
    .config(routes);

export default gameModule.name;