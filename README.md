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
