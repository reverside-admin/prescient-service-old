/**
 * Created by Bibhuti on 2014/04/07.
 */

var password_app = angular.module('password_app', ['ngRoute', 'ngCookies']);


password_app.controller('password_app_controller', function ($scope, $http, $location, $cookieStore, $window) {
    console.log('password app controller is loaded');
    $scope.user;
    $scope.password;
    $scope.confirm_password;
    $scope.user_role;
    $scope.user_detail={};
    $scope.error_message;

    if ($cookieStore.get("user") == null) {
        console.log("User is not authenticated");
        $window.location.replace("login-app.html");
    }

    $scope.validate=function(change_password_form)
    {
        if(change_password_form.$valid && ($scope.password == $scope.user_detail.password))
        {
            $scope.changePassword();
        }
        if(($scope.password == null)&& ($scope.user_detail.password==null))
        {

        }
        else{
            $scope.error_message='password and confirm password are not matched';
        }

    }
    $scope.changePassword=function()
    {
        $scope.user=$cookieStore.get("user");
        console.log('password changed...');
        console.log($scope.password);
        console.log($scope.user_detail.password);
        console.log($scope.user.userType.type);
         <!-- call web service to reset password-->

        $http({
            url: 'http://localhost:8080/api/users/resetPasswordUser/' + $scope.user.id,
            method: 'put',
            headers: { 'Content-Type': 'application/json',
                'Authorization': $cookieStore.get("auth")
            },
            data: $scope.user_detail
        }).
            success(function (data, status) {
                if (status == 201) {

                    if($scope.user.userType.type=='ROLE_ADMIN')
                        $window.location.replace("admin-app.html");

                    if($scope.user.userType.type=='ROLE_STAFF')
                        $window.location.replace("staff-app.html");

                    if($scope.user.userType.type=='ROLE_MANAGER')
                        $window.location.replace("manager-app.html");

                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
    }
});

