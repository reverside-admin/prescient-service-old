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
            .when('/users/accesscard', {
                'templateUrl': 'ui/users/access-card.html',
                'controller': 'access_card_controller'
            })
            .when('/users/touchpoint', {
                'templateUrl': 'ui/users/touch-point.html',
                'controller': 'touch_point_controller'
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
            .when('/users/:uId/departments/add', {
                'templateUrl': 'ui/users/add-department.html',
                'controller': 'add_departments_controller'
            })
            .when('/users/:uId/touchpoints/add', {
                'templateUrl': 'ui/users/add-touchpoint.html',
                'controller': 'add_touch_points_controller'
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
        $cookieStore.remove("auth");
        $window.location.replace("login-app.html");
    };

});

<!--list user  controller -->
admin_app.controller('list_users_controller', function ($scope, $http, $routeParams, $cookieStore) {

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

<!-- TODO: basic authentication failed,browser popup is coming -->
admin_app.controller('view_users_controller', function ($scope, $http, $routeParams, $cookieStore) {
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

admin_app.controller('update_users_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {
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

    $scope.resetPassword=function()
    {
        $http({
            url: 'http://localhost:8080/api/users/resetPasswordAdmin/' + $scope.uId,
            method: 'put',
            headers: { 'Content-Type': 'application/json'},
            data: $scope.user,
            'Authorization': $cookieStore.get("auth")

        }).
            success(function (data, status) {
                if (status == 201) {
                    console.log('password is reset successfully');
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


admin_app.controller('create_users_controller', function ($scope, $http, $location, $cookieStore) {

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


admin_app.controller('add_departments_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {
    $scope.dept_list_assigned = [];
    $scope.dept_list_not_assigned = [];
    $scope.selected_assigned_dept;
    $scope.selected_not_assigned_dept;


    console.log('add department controller is loaded');
    console.log('user id passed from URL is::' + $routeParams.uId);

    $http({
        url: 'http://localhost:8080/api/hotels/' + $routeParams.uId + '/dept/notHaving',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.dept_list_not_assigned = data;
                console.log('department list not assigned:' + $scope.dept_list_not_assigned);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


    $http({
        url: 'http://localhost:8080/api/hotels/' + $routeParams.uId + '/dept/having',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")

        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.dept_list_assigned = data;
                console.log('department list assigned:' + $scope.dept_list_assigned);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });
<!--TODO: Handle Proper push and pop from the element -->
    $scope.pushDataFromLeft = function () {
        console.log($scope.selected_not_assigned_dept);
        if ($scope.selected_not_assigned_dept != null) {
            $scope.dept_list_assigned.push($scope.selected_not_assigned_dept);
            $scope.dept_list_not_assigned.pop($scope.selected_not_assigned_dept);
        }

    }

    $scope.pushDataFromRight = function () {
        console.log($scope.selected_assigned_dept);
        if($scope.selected_assigned_dept != null ){
            $scope.dept_list_not_assigned.push($scope.selected_assigned_dept);
            $scope.dept_list_assigned.pop($scope.selected_assigned_dept);
        }
    }


    $scope.addDepartments=function()
    {
        console.log('add departments......');
        $http({
            url: 'http://localhost:8080/api/users/assignDept/' + $routeParams.uId,
            method: 'put',
            headers: {'Content-Type': 'application/json'},
            data:$scope.dept_list_assigned ,
            'Authorization': $cookieStore.get("auth")

        }).
            success(function (data, status) {
                if (status == 201) {

                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
    }

});


admin_app.controller('delete_users_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {
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

admin_app.controller('access_card_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {

});

admin_app.controller('touch_point_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {

});

admin_app.controller('add_touch_points_controller', function ($scope, $http, $routeParams, $location, $cookieStore) {

    $scope.touchpoint_list_assigned = [];
    $scope.touchpoint_list_not_assigned = [];
    $scope.selected_assigned_touchpoint;
    $scope.selected_not_assigned_touchpoint;
    console.log('add touch point controller is added.....');
    $http({
        url: 'http://localhost:8080/api/users/' + $routeParams.uId + '/tp/notHaving',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.touchpoint_list_not_assigned = data;
                console.log('touch point list not assigned:' + $scope.touchpoint_list_not_assigned);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


    $http({
        url: 'http://localhost:8080/api/users/' + $routeParams.uId + '/tp/having',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.touchpoint_list_assigned = data;
                console.log('touch point list assigned:' + $scope.touchpoint_list_assigned);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });
    $scope.show=function()
    {
        console.log('show function is invoked');
    }

    $scope.pushDataFromLeft=function()
    {
       console.log('push data from left');
        if ($scope.selected_not_assigned_touchpoint != null) {
            $scope.touchpoint_list_assigned.push($scope.selected_not_assigned_touchpoint);
            $scope.touchpoint_list_not_assigned.pop($scope.selected_not_assigned_touchpoint);
        }
    }

    $scope.pushDataFromRight=function()
    {
        console.log('push data from Right');
        if($scope.selected_assigned_touchpoint != null ){
            $scope.touchpoint_list_not_assigned.push($scope.selected_assigned_touchpoint);
            $scope.touchpoint_list_assigned.pop($scope.selected_assigned_touchpoint);
        }
    }

    $scope.addTouchPoints=function()
    {
        console.log('add touch point...');
        $http({
            url: 'http://localhost:8080/api/users/assignTP/' + $routeParams.uId,
            method: 'put',
            headers: {'Content-Type': 'application/json'},
            data:$scope.touchpoint_list_assigned ,
            'Authorization': $cookieStore.get("auth")
        }).
            success(function (data, status) {
                if (status == 201) {

                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });
    }

});

