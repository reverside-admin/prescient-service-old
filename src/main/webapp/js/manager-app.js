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
            .when('/hotels/guests/checkedin', {
                'templateUrl': 'ui/find-checkedin-guest.html',
                'controller': 'checkedin_guest_controller'
            })

            .when('/hotels/guests/:guestId/position', {
                'templateUrl': 'ui/current-guest-location.html',
                'controller': 'current_guest_location_controller'
            })
            .when('/hotels/guests/:guestId/location/history', {
                'templateUrl': 'ui/guest-location-history.html',
                'controller': 'current_guest_location_history_controller'
            })
            .when('/manager/guest/contacts', {
                'templateUrl': 'ui/guest-contact-list.html',
                'controller': 'guest_contact_list_controller'
            })
            .when('/manager/guest/contact/create', {
                'templateUrl': 'ui/create-guest-contact.html',
                'controller': 'create_guest_contact_controller'
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


manager_app.controller('current_guest_location_history_controller', function ($scope, $cookieStore, $routeParams, $http) {
    console.log('current_guest_location_history_controller of manager app module is loaded');
    $scope.guest_id = $routeParams.guestId;
    $scope.current_guest_location_history;

    $http({
        url: 'http://localhost:8080/api/guests/' + $scope.guest_id + '/location/history',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.current_guest_location_history = data;
                console.log('current guest location history ::' + $scope.current_guest_location_history);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


});


manager_app.controller('guest_contact_list_controller', function ($scope, $cookieStore, $routeParams, $http) {
    console.log('guest_contact_list_controller of manager app module is loaded');


});

manager_app.controller('create_guest_contact_controller', function ($scope, $cookieStore, $routeParams, $http) {
    console.log('create_guest_contact_controller of manager app module is loaded');
    $scope.guest_contact = {};
    $scope.assigned_touch_point_list;
    $scope.guest_list;
    $scope.selected_touch_points = [];

    <!-- get all assigned touch points -->
    $http({
        url: 'http://localhost:8080/api/login/touchpoints',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::' + status);
            if (status == 200) {
                $scope.assigned_touch_point_list = data;
                console.log('All Touch Points::' + $scope.assigned_touch_point_list);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });

   <!-- get all guests in the hotel -->

    $http({
        url: 'http://localhost:8080/api/guests',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::' + status);
            if (status == 200) {
                $scope.guest_list = data;
                console.log('All Touch Points::' + $scope.guest_list);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });




    $scope.onSelect = function (touchpoint_id) {
        console.log('touch point with Id::' + touchpoint_id + ' is clicked');

        for (var i = 0; i < $scope.selected_touch_points.length; i++) {
            if ($scope.selected_touch_points[i] == touchpoint_id) {
                $scope.selected_touch_points.splice(i, 1);
                 return;
            }
        }
        $scope.selected_touch_points.push({"touchPointId":touchpoint_id});

    };


    $scope.create = function () {
        $scope.guest_contact.guestContactListTouchPoint = $scope.selected_touch_points;
        $scope.guest_contact.ownerId=$cookieStore.get('user').id;
        console.log('create the guest contact list');
        console.log('guest contact list name::'+$scope.guest_contact.guestContactListName);
        console.log('owner id of this contact list is::'+$scope.guest_contact.ownerId);
        console.log('touchpoints::' + $scope.guest_contact.guestContactListTouchPoint);
        console.log('selected touch points::'+$scope.selected_touch_points);






        $http({
            url: 'http://localhost:8080/api/guest/contact/create',
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': $cookieStore.get("auth")
            },
            data: $scope.guest_contact
        }).
            success(function (data, status) {
                if (status == 201) {
                    console.log('Guest contact created successfully');
                    console.log($scope.guest_contact.guestContactListName);


                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });







    }


});


manager_app.controller('current_guest_location_controller', function ($scope, $http, $location, $cookieStore, $routeParams, $window) {
    console.log('current guest location  controller is loaded');
    $scope.guest_id = $routeParams.guestId;
    $scope.guest_current_position;
    console.log('current guest location::' + $scope.guest_id);


    $http({
        url: 'http://localhost:8080/api/guests/' + $scope.guest_id + '/locations',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            if (status == 200 && data != null) {

                $scope.guest_current_position = data;
                console.log('current guest position detail ::' + $scope.guest_current_position);

            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });

});


manager_app.controller('checkedin_guest_controller', function ($scope, $http, $location, $cookieStore, $routeParams, $window) {
    console.log('manager checkedin guest controller is loaded');

    $scope.checkedin_guest_list;
    $scope.hotel_id = $cookieStore.get("user").hotel.id;

    <!--  get all checkedin guest list -->
    $http({
        url: 'http://localhost:8080/api/hotels/' + $scope.hotel_id + '/guests/checkedIn',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.checkedin_guest_list = data;
                console.log('checkedin guest list::' + $scope.checkedin_guest_list);
            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });

});


manager_app.controller('touch_point_setup_list_controller', function ($scope, $http, $location, $cookieStore, $routeParams, $window) {
    console.log('manager touchpoint setup  list controller is loaded');

    $scope.touch_point_setups;
    $scope.current_touch_point_id = $routeParams.tpId;
    $scope.current_setup = {};

    $scope.testModel = -1;

    <!-- get current setup detail -->
    $http({
        url: 'http://localhost:8080/api/touchpoint/' + $scope.current_touch_point_id + '/currentsetup',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            if (status == 200) {
                $scope.current_setup = data;
                console.log('current setup name::' + $scope.current_setup.setupName);
                $scope.testModel = $scope.current_setup.id;

            } else {
                console.log('status:' + status);
            }
        })
        .error(function (error) {
            console.log(error);
        });


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


    $scope.setCurrentSetup = function () {
        console.log('set current setup method is called');

        $http({
            url: 'http://localhost:8080/api/touchpoint/' + $scope.current_touch_point_id + '/setup/' + $scope.testModel,
            method: 'get',
            headers: {
                'Authorization': $cookieStore.get("auth")
            }
        }).
            success(function (data, status) {
                if (status == 200) {
                    console.log('setup successfully done');
                    $location.url('manager/touchpoint/list');
                } else {
                    console.log('status:' + status);
                }
            })
            .error(function (error) {
                console.log(error);
            });

    };
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


manager_app.controller('touch_point_controller', function ($scope, $cookieStore, $http) {
    console.log('touch point controller of manager app module is loaded');
    $scope.assigned_touch_point_list = [];
    $scope.selected_touch_point;
    $scope.user_detail = $cookieStore.get("user");
    console.log('current user id::' + $scope.user_detail.id);

    $http({
        url: 'http://localhost:8080/api/login/touchpoints',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::' + status);
            if (status == 200) {
                $scope.assigned_touch_point_list = data;
                console.log('All Touch Points::' + $scope.assigned_touch_point_list);
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
    $scope.guest_list = [];

    $http({
        url: 'http://localhost:8080/api/touchpoints/' + $routeParams.TPId + '/guestCards',
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::' + status);
            if (status == 200) {
                $scope.guest_list = data;
                console.log('All Guest List::' + $scope.guest_list);
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
    $scope.guest_id = $routeParams.guestId;
    console.log('guest detail controller is loaded...' + $scope.guest_id);


    $http({
        url: 'http://localhost:8080/api/guest/' + $scope.guest_id,
        method: 'get',
        headers: {
            'Authorization': $cookieStore.get("auth")
        }
    }).
        success(function (data, status) {
            console.log('get success code::' + status);
            if (status == 200) {
                $scope.guest_detail = data;
                console.log('Guest Detail::' + $scope.guest_detail);

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


