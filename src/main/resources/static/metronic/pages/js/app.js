angular.module('myApp', ['ngRoute']).config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/terminal_list', {
        templateUrl: 'views/fragment/terminal_list.html',
        controller: 'TerminalListController'
    }).when('/add_terminal', {
        templateUrl: 'views/fragment/add_terminal.html',
        controller: 'AddTerminalController'
    }).otherwise({
        redirectTo: '/home'
    });
}]);