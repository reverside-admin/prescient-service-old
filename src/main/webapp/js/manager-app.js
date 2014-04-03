/**
 * Created by Bibhuti on 2014/04/03.
 */
var manager_app = angular.module('manager_app', ['ngRoute', 'ngCookies']);


manager_app.controller('manager_app_controller', function ($scope, $http, $location, $cookieStore, $window) {
console.log('manager app controller is loaded');
    $scope.user;

    if ($cookieStore.get("user") == null) {
        console.log("User is not authenticated");
        $window.location.replace("login-app.html?manager-app.html" + $window.location.hash);
    } else {
        console.log("User is authenticated");
        $scope.user = $cookieStore.get("user");
        if ($scope.user.userType.type != "ROLE_MANAGER") {
            $window.location.replace("index.html");
        }
    }


    $scope.logout = function () {
        console.log('logout method is called');
        $cookieStore.remove("user");
        $cookieStore.remove("auth");
        $window.location.replace("login-app.html");
    };
});