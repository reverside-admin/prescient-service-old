/**
 * Created by Bibhuti on 2014/04/03.
 */
var manager_app = angular.module('manager_app', ['ngRoute', 'ngCookies']);


manager_app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/manager/touchpoints', {
                'templateUrl': 'ui/touch_point.html',
                'controller': 'touch_point_controller'
            })
            .when('/manager/touchpoint/list', {
                'templateUrl': 'ui/manager-touchpoint.html',
                'controller': 'touch_point_controller'
            })
            .when('/manager/touchpoint/:tpId/setups', {
                'templateUrl': 'ui/manager-touchpoint-setup-list.html',
                'controller': 'touch_point_setup_list_controller'
            })
            .when('/touchpoint/:TPId/guests', {
                'templateUrl': 'ui/find-guest.html',
                'controller': 'find_guest_controller'
            })
            .when('/guest/:guestId', {
                'templateUrl': 'ui/guest-detail.html',
                'controller': 'guest_detail_controller'
            })
            .when('/welcome', {
                'templateUrl': '/ui/welcome-page.html'
             })
            .otherwise({
                redirectTo: '/welcome'
            });
    }]);


manager_app.controller('touch_point_setup_list_controller', function ($scope, $http, $location, $cookieStore,$routeParams, $window) {
console.log('manager touchpoint setup  list controller is loaded');
    $scope.touch_point_setups;
    $scope.current_touch_point_id = $routeParams.tpId;

    <!-- get all setups by touchpointid -->

    $http({
        url: 'http://localhost:8080/api/tp/' + $routeParams.tpId + '/setups',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.touch_point_setups = data;
                console.log('touch point setups::' + $scope.touch_point_setups);
                console.log($scope.touch_point_setups.length);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


    $scope.setCurrentSetup=function()
    {
        console.log('set current setup method is called');
    }
});




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



manager_app.controller('touch_point_controller', function ($scope,$cookieStore,$http) {
    console.log('touch point controller of manager app module is loaded');
    $scope.assigned_touch_point_list=[];
    $scope.selected_touch_point;
    $scope.user_detail=$cookieStore.get("user");
    console.log('current user id::'+$scope.user_detail.id);

    $http({
        url: 'http://localhost:8080/api/login/touchpoints',
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

manager_app.controller('find_guest_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {
console.log(' manager guest find controller is loaded');
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


manager_app.controller('guest_detail_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {

    $scope.guest_detail;
    $scope.guest_id=$routeParams.guestId;
    console.log('guest detail controller is loaded...'+$scope.guest_id);



    $http({
        url: 'http://localhost:8080/api/guest/'+$scope.guest_id,
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::'+status);
            if (status == 200) {
                $scope.guest_detail = data;
                console.log('Guest Detail::'+$scope.guest_detail);

                console.log($scope.guest_detail.arrivalTime);
                console.log($scope.guest_detail.departureTime);

            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });

});


