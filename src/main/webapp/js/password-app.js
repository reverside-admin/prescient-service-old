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
            console.log('validated the reset password form');
            $scope.changePassword();
            return;
        }
        if(($scope.password == null) && ($scope.user_detail.password==null))
        {

        }
        else{
            $scope.error_message='password and confirm password are not matched';
        }

    }
    $scope.changePassword=function()
    {
        console.log('inside the change password function....');
        $scope.user=$cookieStore.get("user");
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
                console.log('password changed successfully');

                <!-- update auth in cookie-->

                var token = 'Basic ' + $scope.decode($scope.user.userName + ':' + $scope.user_detail.password);

                $cookieStore.remove("auth");
                $cookieStore.put("auth", token);



                    if($scope.user.userType.type=='ROLE_ADMIN')
                        $window.location.replace("admin-app.html");

                    if($scope.user.userType.type=='ROLE_STAFF')
                        $window.location.replace("staff-app.html");

                    if($scope.user.userType.type=='ROLE_MANAGER')
                        $window.location.replace("manager-app.html");



            })
            .error(function (error) {
                console.log(error);
            });
    };


    $scope.decode = function (input) {
        var keyStr = 'ABCDEFGHIJKLMNOP' +
            'QRSTUVWXYZabcdef' +
            'ghijklmnopqrstuv' +
            'wxyz0123456789+/' +
            '=';
        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;

        do {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
                keyStr.charAt(enc1) +
                keyStr.charAt(enc2) +
                keyStr.charAt(enc3) +
                keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        } while (i < input.length);

        return output;
    }





});

