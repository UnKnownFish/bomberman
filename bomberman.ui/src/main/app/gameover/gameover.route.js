export default function routes($stateProvider) {
    "ngInject";
    $stateProvider.state('gameover', {
        url: "/gameover",
        template: require('./gameover.html'),
        controller: 'MainController',
        controllerAs: 'gameOverCtrl'
    });
}