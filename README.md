prescient-service
=================
Procedure to Run the prescient-service project :
1> mvn clean
2> mvn package
3> mvn spring-boot:run

URL to check :
1>  http://localhost:8080/api/users
    Get all User List
2>  http://localhost:8080/api/users/{userId}
    Get a single User Details
3> http://localhost:8080/api/users/{userName}/login
    Login
4>  http://localhost:8080/api/users/status
    Get All Status
5>  http://localhost:8080/api/users/roles
    Get All Roles
6>  http://localhost:8080/api/hotels
    Get All Hotel list
7>  http://localhost:8080/api/hotels/{hotelId}
    Get a single Hotel
8>  http://localhost:8080/api/users/hotel/{hotelId}/departments
    Get department list as per hotelID
9>  http://localhost:8080/api/hotels/{hotelId}/departments/{departmentId}/touchpoints
    Touch point list as per hotelId and departmentId
