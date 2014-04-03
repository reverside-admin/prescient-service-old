/**
 * Created by Bibhuti on 2014/04/03.
 */
var staff_app = angular.module('staff_app', ['ngRoute', 'ngCookies']);


staff_app.controller('staff_app_controller', function ($scope, $http, $location, $cookieStore, $window) {
    console.log('staff app controller is loaded');
    $scope.user;

    if ($cookieStore.get("user") == null) {
        console.log("User is not authenticated");
        $window.location.replace("login-app.html?staff-app.html" + $window.location.hash);
    } else {
        console.log("User is authenticated");
        $scope.user = $cookieStore.get("user");
        if ($scope.user.userType.type != "ROLE_STAFF") {
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
