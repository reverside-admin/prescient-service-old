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
            headers: {
            }
        }).success(function (data, status) {
            console.log('status:' + status + ' response:' + data);
            if (status == 200) {
                var token = 'Basic ' + $scope.decode( user_name + ':' + password );
                $cookieStore.put("user", data);
                $cookieStore.put("auth",token);
                var redirect_url = $window.location.search.replace('?','') + $window.location.hash;
                if(redirect_url == ""){
                   redirect_url = "index.html";
                }
                console.log("Redirect To URL : " + redirect_url);
                $window.location.replace(redirect_url);
            } else {
                $scope.error = "Invalid Username or Password";
            }
        }).error(function (error,status) {
            <!--console.log(error);-->
            <!--$scope.error = error;-->
              console.log('status code::'+status);
              if(status==500)
              {
                  $scope.error='Invalid UserName and Password';
               }
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
