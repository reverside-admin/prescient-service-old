/**
 * Created by Bibhuti on 2014/03/29.
 */
var login_app = angular.module('login_app', ['ngRoute', 'ngCookies']);

login_app.controller("login_app_controller", function ($scope, $http, $cookieStore, $window) {

    $scope.error;

    $scope.login = function (user_name, password) {
        var url = 'http://localhost:8080/login/' + user_name + '/' + password;
        console.log(url);
        $http({
            method: 'GET',
            url: url,
            headers: {}
        }).success(function (data, status) {
            console.log('status:' + status + ' response:' + data);
            if (status == 200) {
                $cookieStore.put("user", data);
                var redirect_url = $window.location.search.replace('?','') + $window.location.hash;
                if(redirect_url == ""){
                   redirect_url = "index.html";
                }
                console.log("Redirect To URL : " + redirect_url);
                $window.location.replace(redirect_url);
            } else {
                $scope.error = "Invalid Username or Password";
            }
        }).error(function (error) {
            console.log(error);
            $scope.error = error;
        });

    };
});
