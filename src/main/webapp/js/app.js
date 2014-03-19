var sampleApp = angular.module('sampleApp', ['ngRoute']);


sampleApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/admin', {
                templateUrl: 'views/admin/home.html',
                controller: 'AdminController'
            }).

            when('/staff', {
                templateUrl: 'views/staff/home.html',
                controller: 'StaffController'
            }).

            when('/manager', {
                templateUrl: 'views/manager/home.html',
                controller: 'ManagerController'
            }).

            when('/login', {
                templateUrl: 'login.html',
                controller: 'LoginController'
            }).

            when('/logout', {
                templateUrl: 'login.html',
                controller: 'LogoutController'
            }).
            otherwise({
                redirectTo: '/login'
            });
    }]);


sampleApp.controller('LoginController', function ($scope, $http, $location) {
    $scope.user;
    $scope.userName;
    $scope.password;

    $scope.doLogin = function () {
        var url = 'http://localhost:8080/service/user/' + $scope.userName;
        var token = 'Basic ' + $scope.decode($scope.userName + ':' + $scope.password);
        console.log(url);
        $http({
            method: 'GET',
            url: url,
            headers: {
                'Authorization': token
            }
        })
            .success(function (data, status) {
                $scope.user = data;
                $scope.message;
                console.log($scope.user.userType.type);
                if ($scope.user.userType.type == 'admin') {
                    $location.url('/admin');
                    $scope.message = 'Welcome' + $scope.user.userName;
                }
                if ($scope.user.userType.type == 'staff') {
                    $location.url('/staff');
                    $scope.message = 'Welcome' + $scope.user.userName;
                }
                if ($scope.user.userType.type == 'manager') {
                    $location.url('/manager');
                    $scope.message = 'Welcome' + $scope.user.userName;
                }
            }).error(function (error) {
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

sampleApp.controller('AddOrderController', function ($scope) {

    $scope.message = 'This is Add new order screen';

});


sampleApp.controller('AdminController', function ($scope) {

    $scope.message = 'user name';

});

sampleApp.controller('StaffController', function ($scope) {

    $scope.message = 'This is user screen';

});


sampleApp.controller('ManagerController', function ($scope) {

    $scope.message = 'This is manager screen';

});


sampleApp.controller('LogoutController', function ($scope) {

    $scope.message = 'do logout action';

});


