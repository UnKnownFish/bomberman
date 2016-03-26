import 'babel-polyfill';
import 'angular/angular.js';
import bootstap from 'angular-ui-bootstrap';
import router from 'angular-ui-router';
import config from './config.js';
import game from './game/game.module.js';
import main from './main/main.module.js';
import gameover from './gameover/gameover.module.js';

// Create our demo module
let bombermanModule = angular.module('bomberman', [
    bootstap,
    router,
    game,
    main,
    gameover
]).config(config);

export default bombermanModule;