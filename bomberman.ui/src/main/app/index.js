import 'babel-polyfill';
import 'angular/angular.js';
import bootstap from 'angular-ui-bootstrap';
import router from 'angular-ui-router';
import config from './config.js';
import game from './game/game.module.js';

// Create our demo module
let bombermanModule = angular.module('bomberman', [
    bootstap,
    router,
    game
]).config(config);

export default bombermanModule;