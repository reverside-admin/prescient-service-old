/**
 * Created by Bibhuti on 2014/04/16.
 */

/**
 * Created by Bibhuti on 2014/03/29.
 */
var login_app = angular.module('login_app1', []);

login_app1.controller("login_app_controller1", function ($scope) {
    $scope.message = "Hello World";

    $scope.login = function(username, password){
        console.log("Login method invoked");
        $scope.message = "I m called";
    };
})
;

