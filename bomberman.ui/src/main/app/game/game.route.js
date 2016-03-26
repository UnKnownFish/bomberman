export default function routes($stateProvider) {
    "ngInject";
    $stateProvider.state('game', {
        url: "/game/:gameId/player/:playerId",
        template: require('./game.html'),
        controller: 'GameController',
        controllerAs: 'gameCtrl'
    });
}