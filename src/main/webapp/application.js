/**
 * Created by Bibhuti on 2014/03/19.
 */
var prescientApp = angular.module('prescientApp', ['ngRoute']);

prescientApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/find-user',{
                'templateUrl'   : 'views/find-user.html',
                'controller'    : 'findUserController'
            })
            .when('/add-user',{
                'templateUrl'   : 'views/add-user.html',
                'controller'    : 'addUserController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);

prescientApp.controller('applicationController', function ($scope, $http, $rootScope) {
    $scope.application = "login.html";
    $scope.errorMessage;
    $scope.doLogin = function (userName, password) {
        var url = 'http://localhost:8080/api/user/' + userName;
        var token = 'Basic ' + $scope.decode(userName + ':' + password);
        console.log(url);
        $http({
            method: 'GET',
            url: url,
            headers: {
                'Authorization': token
            }
        }).success(function (data, status) {
            /*
             TODO : Check Status Code and Take Action Accordingly
             */
            console.log('response : ' + data.userType.type);
            $rootScope.user = data;
            if (data.userType.type == 'admin') {
                $scope.application = "views/admin-home.html";
            }
            if (data.userType.type == 'staff') {
                $scope.application = "views/staff-home.html";
            }
            if (data.userType.type == 'manager') {
                $scope.application = "views/manager-home.html";
            }
        }).error(function (error) {
            console.log(error);
            $scope.errorMessage = error;
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


prescientApp.controller('adminHomeController', function ($scope, $http, $rootScope) {
    $scope.user = $rootScope.user;
});

prescientApp.controller('findUserController', function ($scope, $http) {

});


prescientApp.controller('addUserController', function ($scope, $http) {

});

