import "angular/angular.js";
import router from "angular-ui-router";
import routes from "./gameover.route.js";

let gameOverModule = angular.module("gameover", [])
    .config(routes);

export default gameOverModule.name;