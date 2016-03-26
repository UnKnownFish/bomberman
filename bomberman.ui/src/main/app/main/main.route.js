export default function routes($stateProvider) {
    "ngInject";
    $stateProvider.state('main', {
        url: "/main",
        template: require('./main.html'),
        controller: 'MainController',
        controllerAs: 'mainCtrl'
    });
}