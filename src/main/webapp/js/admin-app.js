/**
 * Created by Bibhuti on 2014/03/19.
 */
var admin_app = angular.module('admin_app', ['ngRoute', 'ngCookies']);

admin_app.config(['$routeProvider',
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
                'controller': 'view_users_controller'
            })
            .when('/users/update/:userId', {
                'templateUrl': 'ui/users/update.html',
                'controller': 'update_users_controller'
            })
            .when('/users/delete/:userId', {
                'templateUrl': 'ui/users/delete.html',
                'controller': 'delete_users_controller'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);


admin_app.controller('admin_app_controller', function ($scope, $http, $location, $cookieStore, $window) {

    $scope.user;

    if ($cookieStore.get("user") == null) {
        console.log("User is not authenticated");
        $window.location.replace("login-app.html?admin-app.html" + $window.location.hash);
    } else {
        console.log("User is authenticated");
        $scope.user = $cookieStore.get("user");
        if ($scope.user.userType.type != "ROLE_ADMIN") {
            $window.location.replace("index.html");
        }
    }


    $scope.logout = function () {
        $http({
            url: "http://localhost:8080/j_spring_security_logout",
            method: "get",
            headers: {}
        })
            .success(function (data, status) {
                if (status == 200) {
                    $cookieStore.remove("user");
                    $window.location.replace("login-app.html");
                }else{
                    console.log("Failed to logout");
                }

            })
            .error(function () {
                console.log("Failed to logout");
            });

    };

});

<!--list user  controller -->
admin_app.controller('list_users_controller', function ($scope, $http, $routeParams) {

    $scope.user_list = [];
    $scope.name = $routeParams.userName;
    $scope.user = {};

    $http({
        url: 'http://mrunmay:secret@localhost:8080/api/users',
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
});


admin_app.controller('view_users_controller', function ($scope, $http, $routeParams) {
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

admin_app.controller('update_users_controller', function ($scope, $http, $routeParams, $location) {
    $scope.uId = $routeParams.userId;
    $scope.user = {};
    $http({
        url: 'http://localhost:8080/api/users/' + $scope.uId,
        method: 'get',
        headers: {}
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.user = data;
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });

    $scope.update = function () {
        console.log('update');

        $http({
            url: 'http://localhost:8080/api/users/update/' + $scope.uId,
            method: 'put',
            headers: { 'Content-Type': 'application/json'},
            data: $scope.user
        }).
            success(function (data, status) {
                if (status == 201) {
                    console.log('User updated successfully');
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
    }

    $scope.cancel = function () {
        $location.url('/users/list');
    }
});


admin_app.controller('create_users_controller', function ($scope, $http, $location) {

    $scope.hotel_list = [];
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
                $scope.hotel_list = data;
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
        console.log($scope.user);
        $http({
            url: 'http://localhost:8080/api/users',
            method: 'post',
            headers: { 'Content-Type': 'application/json'},
            data: $scope.user
        }).
            success(function (data, status) {
                if (status == 201) {
                    console.log('User created successfully');
                    $location.url('/users/list');
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });

    };

    $scope.cancel = function () {
        $location.url('/users/list');
    }


});


<!-- Delete user controller -->

admin_app.controller('delete_users_controller', function ($scope, $http, $routeParams, $location) {
    console.log('delete user controller is loaded');
    $scope.uId = $routeParams.userId;
    $scope.user_detail = {};
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


    $scope.delete = function () {
        console.log('delete');
        $http({
            url: 'http://localhost:8080/api/users/delete/' + $scope.uId,
            method: 'put',
            headers: { 'Content-Type': 'application/json'},
            data: $scope.user
        }).
            success(function (data, status) {
                if (status == 201) {
                    $location.url('users/list');
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
    }
});


