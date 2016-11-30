angular.module('app').config(appConfigRoute);

appConfigRoute.$inject = ['$routeProvider', '$locationProvider']

function appConfigRoute($routeProvider, $locationProvider) {

    $routeProvider

    .when('/home', {
        templateUrl: 'partials/home/home.html',
        controller: 'HomeController',
        controllerAs: 'vm'
    })

    .otherwise('/home');

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });

}
