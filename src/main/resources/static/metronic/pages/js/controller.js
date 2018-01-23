myApp.controller('TerminalListController', ['$rootScope', '$scope', '$http', function ($rootScope, $scope, $http) {
    $scope.$on('$viewContentLoaded', function () {
        console.log("页面加载完成");
    });
}]);