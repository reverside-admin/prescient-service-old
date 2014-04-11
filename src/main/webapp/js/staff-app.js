/**
 * Created by Bibhuti on 2014/04/03.
 */
var staff_app = angular.module('staff_app', ['ngRoute', 'ngCookies']);


staff_app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/staffs/touchpoints', {
                'templateUrl': 'ui/touch_point.html',
                'controller': 'touch_point_controller'
            })
            .when('/touchpoint/:TPId/guests', {
                'templateUrl': 'ui/find-guest.html',
                'controller': 'find_guest_controller'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);




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


staff_app.controller('touch_point_controller', function ($scope,$cookieStore,$http) {
console.log('touch point controller of staff app module is loaded');
    $scope.assigned_touch_point_list=[];
    $scope.selected_touch_point;
    $scope.user_detail=$cookieStore.get("user");
    console.log('current user id::'+$scope.user_detail.id);

    $http({
        url: 'http://localhost:8080/api/users/'+$scope.user_detail.id+'/tp/having',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::'+status);
            if (status == 200) {
                $scope.assigned_touch_point_list = data;
                console.log('All Touch Points::'+$scope.assigned_touch_point_list);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });
});

staff_app.controller('find_guest_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {
    console.log(' staff guest find controller is loaded');
    $scope.guest_list=[];

    $http({
        url: 'http://localhost:8080/api/touchpoints/'+ $routeParams.TPId + '/guestCards',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::'+status);
            if (status == 200) {
                $scope.guest_list = data;
                console.log('All Guest List::'+$scope.guest_list);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });
});
