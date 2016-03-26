import "angular/angular.js";
import router from "angular-ui-router";
import routes from "./gameover.route.js";
import GameOverController from "./gameover.controller.js";

let gameOverModule = angular.module("gameover", [])
    .controller("GameOverController", GameOverController)
    .config(routes);

export default gameOverModule.name;