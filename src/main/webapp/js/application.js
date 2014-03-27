/**
 * Created by Bibhuti on 2014/03/19.
 */
var prescientApp = angular.module('prescientApp', ['ngRoute']);

prescientApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/users/list', {
                'templateUrl': 'ui/users/list.html',
                'controller': 'list_users_controller'
            })
            .when('/users/create', {
                'templateUrl': 'ui/users/create.html',
                'controller': 'create_users_controller'
            })
            .when('/users/view/:userId', {
                'templateUrl': 'ui/users/view.html',
                'controller': 'view_user_controller'
            })
            .when('/users/update/:userId', {
                'templateUrl': 'ui/users/update.html',
                'controller': 'update_user_controller'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);

prescientApp.controller('applicationController', function ($scope, $http, $location) {
    $location.path('/');
    $scope.application_page = 'login.html';
    $scope.application_user;
    $scope.error_message;
    $scope.user_name = 'mrunmay';
    $scope.password = 'secret';

    $scope.doLogin = function (user_name, password) {
        if ((user_name != null) && (password != null)) {

            var url = 'http://' + user_name + ':' + password + '@localhost:8080/api/users/' + user_name + '/login';
            console.log(url);
            $http({
                method: 'GET',
                url: url,
                headers: {}
            }).success(function (data, status) {
                console.log('status:' + status + ' response:' + data);
                if (status == 200) {
                    $scope.application_user = data;
                    if (data.userType.type == 'ROLE_ADMIN') {
                        $scope.application_page = "ui/admin-home.html";
                    }
                    if (data.userType.type == 'ROLE_STAFF') {
                        $scope.application_page = "ui/staff-home.html";
                    }
                    if (data.userType.type == 'ROLE_MANAGER') {
                        $scope.application_page = "ui/manager-home.html";
                    }
                }

            }).error(function (error) {
                console.log(error);
                $scope.error_message = error;
            });
        }
        else {
            console.log('invalid credentials');
            $scope.error_message = 'Please Enter Credential Detail !!';
        }
    };
});


prescientApp.controller('adminHomeController', function ($scope, $http, $rootScope) {
    $scope.user = $rootScope.user;
});


prescientApp.controller('view_user_controller', function ($scope, $http, $routeParams) {
    $scope.uId = $routeParams.userId;
    $scope.user_detail;


    $http({
        url: 'http://localhost:8080/api/users/' + $scope.uId,
        method: 'get',
        headers: {}
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.user_detail = data;
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });
});

prescientApp.controller('update_user_controller', function ($scope, $http, $routeParams) {
    $scope.uId = $routeParams.userId;
    $scope.user_detail;
    $http({
        url: 'http://localhost:8080/api/users/' + $scope.uId,
        method: 'get',
        headers: {}
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.user_detail = data;
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });
    $scope.saveUpdateUser = function () {
        console.log('updated successfully');
        $http.post("uri", $scope.user_detail).success(function (data, status, header) {
            alert('post success');
        })
            .error(function (error) {
                console.log(error);
            });

    }
});


prescientApp.controller('create_users_controller', function ($scope, $http) {

    $scope.hotel_id_list = [];
    $scope.hotel_department_list = [];
    $scope.touch_point_list = [];
    $scope.user_type_list = [];
    $scope.user_status_list = [];
    $scope.user = {};

    $http({
        url: 'http://localhost:8080/api/roles',
        method: 'get',
        headers: {}
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.user_type_list = data;
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


    $http({
        url: 'http://localhost:8080/api/status',
        method: 'get',
        headers: {}
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.user_status_list = data;
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


    $http({
        url: 'http://localhost:8080/api/hotels',
        method: 'get',
        headers: {}
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.hotel_id_list = data;
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


    $scope.getDepartments = function () {

        $http({
            url: 'http://localhost:8080/api/hotels/' + 1 + '/departments',
            method: 'get',
            headers: {}
        }).
            success(function (data, status) {
                if (status == 200) {
                    $scope.hotel_department_list = data;
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
    };


    $scope.create = function () {

        $http({
            url: 'http://localhost:8080/api/users',
            method: 'post',
            headers: { 'Content-Type': 'application/json'},
            data: $scope.user
        }).
            success(function (data, status) {
                if (status == 201) {
                    console.log('User created successfully');
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });

    };


});


<!--list user  controller -->
prescientApp.controller('list_users_controller', function ($scope, $http, $routeParams) {

    $scope.user_list = [];
    $scope.name = $routeParams.userName;

    $http({
        url: 'http://localhost:8080/api/users',
        method: 'get',
        headers: {}
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.user_list = data;
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


    $scope.removeUser = function () {

    }


});



