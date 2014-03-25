prescient-service
=================
Procedure to Run the prescient-service project :
1> mvn clean
2> mvn package
3> mvn spring-boot:run

Mapped URL path :

User Detail
------------
1>  http://localhost:8080/api/users
    Get all User List
2>  http://localhost:8080/api/users/{userId}
    Get a single User Details
3>  http://localhost:8080/api/users/{userName}/login
    For Login by UserName

Hotel
-----
1>  http://localhost:8080/api/hotels
    Get All Hotel list
2>  http://localhost:8080/api/hotels/{hotelId}
    Get a single Hotel
3>  http://localhost:8080/api/hotels/{hotelId}/departments
    Get department list as per HotelID

Touch Point
-----------
1>  http://localhost:8080/api/departments/{departmentId}/touchpoints
    Get All touch points as per DepartmentId

User Type
---------
5>  http://localhost:8080/api/roles
    Get All Roles

User Status
-----------
1>  http://localhost:8080/api/status
    Get All Status

Department
----------

