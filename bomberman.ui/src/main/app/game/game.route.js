export default function routes($stateProvider) {
    "ngInject";
    $stateProvider.state('game', {
        url: "/game",
        template: require('./game.html'),
        controller: 'GameController',
        controllerAs: 'gameCtrl'
    });
}