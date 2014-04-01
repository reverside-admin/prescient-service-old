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
            .when('/users/add/departments/:userName', {
                'templateUrl': 'ui/users/add-department.html',
                'controller': 'add_department_controller'
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
        console.log('logout method is called');
        $cookieStore.remove("user");
        $window.location.replace("login-app.html");
    };

});

<!--list user  controller -->
admin_app.controller('list_users_controller', function ($scope, $http, $routeParams,$cookieStore) {

    $scope.user_list = [];
    $scope.name = $routeParams.userName;
    $scope.user = {};
     $http({
        url: 'http://localhost:8080/api/users',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
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


admin_app.controller('view_users_controller', function ($scope, $http, $routeParams,$cookieStore) {
    $scope.uId = $routeParams.userId;
    $scope.user_detail;


    $http({
        url: 'http://localhost:8080/api/users/' + $scope.uId,
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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

admin_app.controller('update_users_controller', function ($scope, $http, $routeParams, $location,$cookieStore) {
    $scope.uId = $routeParams.userId;
    $scope.user_status_list = [];
    $scope.user_type_list = [];
    $scope.hotel_list = [];
    $scope.user = {};
    $http({
        url: 'http://localhost:8080/api/users/' + $scope.uId,
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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

    <!-- get status -->
    $http({
        url: 'http://localhost:8080/api/status',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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

    <!-- get user type -->

    $http({
        url: 'http://localhost:8080/api/roles',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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

    <!-- get hotel list -->

    $http({
        url: 'http://localhost:8080/api/hotels',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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


    $scope.update = function () {
        console.log('update');
        console.log($scope.user);
        $http({
            url: 'http://localhost:8080/api/users/update/' + $scope.uId,
            method: 'put',
            headers: { 'Content-Type': 'application/json'},
            data: $scope.user,
            'Authorization': $cookieStore.get("auth")

        }).
            success(function (data, status) {
                if (status == 201) {
                    console.log('User updated successfully');
                    $location.url('/users/list');
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
    }

    $scope.cancel = function () {
        $location.url('/users/view/' + $scope.uId);
    }
});


admin_app.controller('create_users_controller', function ($scope, $http, $location,$cookieStore) {

    $scope.hotel_list = [];
    $scope.hotel_department_list = [];
    $scope.touch_point_list = [];
    $scope.user_type_list = [];
    $scope.user_status_list = [];
    $scope.user = {};

    $http({
        url: 'http://localhost:8080/api/roles',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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


    $scope.create = function () {
        console.log($scope.user);
        $http({
            url: 'http://localhost:8080/api/users',
            method: 'post',
            headers: { 'Content-Type': 'application/json'},
            data: $scope.user,
            'Authorization': $cookieStore.get("auth")

        }).
            success(function (data, status) {
                if (status == 201) {
                    console.log('User created successfully');
                    console.log($scope.user.userName);
                    $location.url('/users/add/departments/' + $scope.user.userName);

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


admin_app.controller('add_department_controller', function ($scope, $http, $routeParams, $location,$cookieStore) {
    console.log('add department controller is loaded');
    console.log('user name passed from create controller is::' + $routeParams.userName);
    $scope.user_name = $routeParams.userName;
    $scope.user;
    $scope.hotel_id;
    console.log('http://localhost:8080/api/users/' + $scope.user_name + '/login');
    $http({
        url: 'http://localhost:8080/api/users/' + $scope.user_name + '/login',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
    }).
        success(function (data, status) {
            if (status == 200) {
                console.log('add department service is successfull');
                $scope.user = data;
                $scope.hotel_id = data.hotel.id;
                console.log('hotel id' + $scope.hotel_id);

            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);

        });


});


admin_app.controller('delete_users_controller', function ($scope, $http, $routeParams, $location,$cookieStore) {
    console.log('delete user controller is loaded');
    $scope.confirm_flag = false;
    $scope.uId = $routeParams.userId;
    $scope.user_detail = {};
    $http({
        url: 'http://localhost:8080/api/users/' + $scope.uId,
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
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
            data: $scope.user,
            'Authorization': $cookieStore.get("auth")

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
    $scope.cancel = function () {
        $location.url('/users/view/' + $scope.uId);
    }

    $scope.showDialog = function () {
        $scope.confirm_flag = true;
    }
});


