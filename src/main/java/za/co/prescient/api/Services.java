package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.*;
import za.co.prescient.repository.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class Services {
    private static final Logger LOGGER = Logger.getLogger(Services.class);

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TouchPointRepository touchPointRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    GuestProfileDetailRepository guestProfileDetailRepository;

    @Autowired
    ItcsTagReadRepository itcsTagReadRepository;

    @Autowired
    GuestCardAllocationRepository guestCardAllocationRepository;


    @RequestMapping(value = "status")
    public List<UserStatus> getAllUserStatus() {
        LOGGER.info("Get All Status service");
        List<UserStatus> userStatuses = userStatusRepository.findAll();
        return userStatuses;
    }

    @RequestMapping(value = "roles")
    public List<UserType> getAllUserRoles() {
        LOGGER.info("Get All Roles service");
        List<UserType> userTypes = userTypeRepository.findAll();
        return userTypes;
    }

    @RequestMapping(value = "hotels")
    public List<Hotel> getAllHotels() {
        LOGGER.info("Get All Hotel service");
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }

    @RequestMapping(value = "hotels/{hotelId}/departments")
    public List<Department> getDepartments(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Get All Departments by HotelId service");
        List<Department> departments = departmentRepository.findByHotelId(hotelId);
        return departments;
    }

    @RequestMapping(value = "departments/tp/all", method = RequestMethod.POST, consumes = "application/json")
    public List<TouchPoint> getAllTouchPointsByDepts(@RequestBody List<Department> departments) {
        LOGGER.info("Get All TPs by DeptIDs");
        List<TouchPoint> touchPoints;
        List<TouchPoint> allTouchPoints = new ArrayList<TouchPoint>();
        for (Department obj : departments) {
            touchPoints = touchPointRepository.findTouchPointByDepartmentId(obj.getId());
            allTouchPoints.addAll(touchPoints);
        }
        return allTouchPoints;
    }

    @RequestMapping(value = "users", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDetail user) {
        LOGGER.info("request received to create user : " + user);
        user.setPassword("password");
        userDetailRepository.save(user);
    }

    @RequestMapping(value = "users/update/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);

        userDetail.setFirstName(user.getFirstName());
        userDetail.setLastName(user.getLastName());

        UserStatus userStatus = new UserStatus();
        userStatus.setId(user.getUserStatus().getId());
        userDetail.setUserStatus(userStatus);

        UserType userType = new UserType();
        userType.setId(user.getUserType().getId());
        userDetail.setUserType(userType);

        List<Department> departments = new ArrayList<Department>();
        departments.addAll(user.getDepartments());
        userDetail.setDepartments(departments);

        List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();
        touchPoints.addAll(user.getTouchPoints());
        userDetail.setTouchPoints(touchPoints);

//        Hotel hotel = new Hotel();
//        hotel.setId(user.getHotel().getId());
//        userDetail.setHotel(hotel);

        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "users/resetPasswordAdmin/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void resetPasswordByAdmin(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setPassword("password");
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "users/resetPasswordUser/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void resetPasswordByUser(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setPassword(user.getPassword());
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "users/{userId}/tp/having")
    public List<TouchPoint> getAssignedTouchPoints(@PathVariable("userId") Long userId) {
        LOGGER.info("Allotted TouchPoints size : " + userDetailRepository.findOne(userId).getTouchPoints().size());
        List<TouchPoint> touchPoints = userDetailRepository.findOne(userId).getTouchPoints();
        return touchPoints;
    }

    @RequestMapping(value = "users/delete/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id")Long id, Principal principal) {

        UserDetail userDetail = userDetailRepository.findOne(id);

        if(userDetail.getUserName() == (principal.getName())){
            throw new RuntimeException("User Can't delete himself");
        }
        else {
            UserStatus userStatus = new UserStatus();
            userStatus.setId(Long.valueOf(1));
            userDetail.setUserStatus(userStatus);
            userDetailRepository.save(userDetail);
        }
    }








    /////////////////////////////////

    @RequestMapping(value = "users/{userName}/login")
    public UserDetail login(@PathVariable("userName") String userName) {
        LOGGER.info("Login Service Start");
        UserDetail userDetail = userDetailRepository.findByUserName(userName);
        LOGGER.info("Login Service End.");
        return userDetail;
    }

    @RequestMapping(value = "users")
    public List<UserDetail> get() {
        LOGGER.info("Get All UserDetails service");
        return userDetailRepository.findAll();
    }

    @RequestMapping(value = "users/{userId}")
    public UserDetail get(@PathVariable("userId") Long userId) {
        LOGGER.info("Get a single UserDetail service");
        return userDetailRepository.findOne(userId);
    }

    @RequestMapping(value = "users/deleteuser/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        UserStatus userStatus = new UserStatus();
        userStatus.setId(Long.valueOf(1));
        userDetail.setUserStatus(userStatus);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "users/assignDept/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignDept(@PathVariable Long id, @RequestBody List<Department> departments) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setDepartments(departments);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "users/{userId}/tp/all")
    public List<TouchPoint> getAllTouchPoints(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        List<Department> departments = userDetail.getDepartments();
        LOGGER.info(departments.size());
        List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();
        List<TouchPoint> allTouchPoints = new ArrayList<TouchPoint>();
        for (Department obj : departments) {
            LOGGER.info("Dept  : " + obj.getId() + " " + obj.getName());
            touchPoints = touchPointRepository.findTouchPointByDepartmentId(obj.getId());
            allTouchPoints.addAll(touchPoints);
            LOGGER.info("touchPoints : " + touchPoints);
            LOGGER.info("touchPoints 1 : " + allTouchPoints);
        }
        LOGGER.info("touchPoints1 : " + touchPoints);

        return allTouchPoints;
    }

    @RequestMapping(value = "users/{userId}/tp/notHaving")
    public List<TouchPoint> getNotAllottedTouchpoints(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        List<Department> departments = userDetail.getDepartments();
        List<TouchPoint> touchPoints;
        List<TouchPoint> allTouchPoints = new ArrayList<TouchPoint>();
        for (Department obj : departments) {
            touchPoints = touchPointRepository.findTouchPointByDepartmentId(obj.getId());
            allTouchPoints.addAll(touchPoints);
        }
        List<TouchPoint> superSet = new ArrayList(allTouchPoints);
        List<TouchPoint> subSet = new ArrayList(userDetailRepository.findOne(userId).getTouchPoints());
        superSet.removeAll(subSet);
        for (TouchPoint obj : superSet) {
            LOGGER.info("TP Not Allotted till now : " + obj.getId() + " " + obj.getName());
        }
        return superSet;
    }

//    @RequestMapping(value = "users/{userId}/tp/having")
//    public List<TouchPoint> getAllottedTouchPoints(@PathVariable("userId") Long userId) {
//        LOGGER.info("Allotted Departments : " + userDetailRepository.findOne(userId).getTouchPoints());
//        return userDetailRepository.findOne(userId).getTouchPoints();
//    }

    @RequestMapping(value = "users/assignTP/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignTP(@PathVariable Long id, @RequestBody List<TouchPoint> touchPoints) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setTouchPoints(touchPoints);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "users/{userId}/guestList")
    public List<GuestProfileDetail> getAllGuest(@PathVariable("userId") Long userId) {
        LOGGER.info("Allotted Departments : " + userDetailRepository.findOne(userId).getTouchPoints());
        userDetailRepository.findOne(userId).getHotel().getId();
        guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId());
        LOGGER.info("GUEST LIST" + guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId()));
        return guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId());
    }


    @RequestMapping(value = "hotels/{hotelId}")
    public Hotel getAHotel(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Get a single Hotel by HotelId service");
        return hotelRepository.findOne(hotelId);
    }


    @RequestMapping(value = "hotels/{userId}/dept/all")
    public List<Department> getAllDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        return departmentRepository.findByHotelId(userDetail.getHotel().getId());
    }

    @RequestMapping(value = "hotels/{userId}/dept/notHaving")
    public List<Department> getNotAllottedDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        List<Department> superSet = new ArrayList(departmentRepository.findByHotelId(userDetail.getHotel().getId()));
        List<Department> subSet = new ArrayList(userDetailRepository.findOne(userId).getDepartments());
        superSet.removeAll(subSet);
        for (Department obj : superSet) {
            LOGGER.info("Dept Not Allotted till now : " + obj.getId() + " " + obj.getName());
        }
        return superSet;
    }

    @RequestMapping(value = "hotels/{userId}/dept/having")
    public List<Department> getAllottedDepartments(@PathVariable("userId") Long userId) {
        LOGGER.info("Allotted Departments : " + userDetailRepository.findOne(userId).getDepartments());
        return userDetailRepository.findOne(userId).getDepartments();
    }

    @RequestMapping(value = "departments/{departmentId}/touchpoints")
    public List<TouchPoint> touchpoints(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Get All Touch Points by DepartmentId service");
        return touchPointRepository.findTouchPointByDepartmentId(departmentId);
    }


    // Test

    @RequestMapping(value = "touchpoints/guestCardIds/all")
    public List<ItcsTagRead> getGuestIds() {
        return itcsTagReadRepository.findAll();
    }

    @RequestMapping(value = "touchpoints/{tpId}/guestCards")
    public List<ItcsTagRead> getGuestIdsByZoneId(@PathVariable("tpId") Integer zoneId) {

        List<ItcsTagRead> itcsTagReadList =itcsTagReadRepository.findTagsInZone(zoneId);

        return itcsTagReadList;
    }





}