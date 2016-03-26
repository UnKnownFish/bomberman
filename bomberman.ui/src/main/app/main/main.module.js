import "angular/angular.js";
import router from "angular-ui-router";
import routes from "./main.route.js";
import MainController from "./main.controller.js";

let mainModule = angular.module("main", [])
    .controller("MainController", MainController)
    .config(routes);

export default mainModule.name;