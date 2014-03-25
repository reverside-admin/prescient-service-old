package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.*;
import za.co.prescient.repository.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserDetailService {
private static final Logger LOGGER = Logger.getLogger(UserDetailService.class);


    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelDepartmentRepository hotelDepartmentRepository;

    @Autowired
    TouchPointRepository touchPointRepository;

    @RequestMapping(value = "{userName}/login")
    public UserDetail login(@PathVariable("userName") String userName) {
        LOGGER.info("Login Service Start");
        UserDetail userDetail = userDetailRepository.findByUserName(userName);
        LOGGER.info("Login Service End.");
        return userDetail;
    }

    @RequestMapping
    public List<UserDetail> get() {
        LOGGER.info("Get All UserDetails service");
        return userDetailRepository.findAll();
    }

    @RequestMapping(value = "{userId}")
    public UserDetail get(@PathVariable("userId") Long userId) {
        LOGGER.info("Get a single UserDetail service");
        return userDetailRepository.findOne(userId);
    }

    @RequestMapping(value = "/roles")
    public List<UserType> getUserRoles() {
        LOGGER.info("Get All Roles service");
        return userTypeRepository.findAll();
    }

    @RequestMapping(value = "/status")
    public List<UserStatus> getUserStatus() {
        LOGGER.info("Get All Status service");
        return userStatusRepository.findAll();
    }

    @RequestMapping(value = "/hotel/{hotelId}/departments")
    public Iterable<HotelDepartment> departments(@PathVariable ("hotelId") Long hotelId) {
        LOGGER.info("Get All Departments by HotelId service");
        return hotelDepartmentRepository.findHotelDepartmentByHotelId(hotelId);
    }

    @RequestMapping(value = "/{departmentId}/touchpoints")
    public Iterable<TouchPoint> touchpoints(@PathVariable ("departmentId") Long departmentId) {
        LOGGER.info("Get All Touch Points by DepartmentId service");
        return touchPointRepository.findTouchPointByHotelDepartmentId(departmentId);
    }

    @RequestMapping(value = "/{hotelId}/{departmentId}/touchpoints")
    public Iterable<TouchPoint> touchpoints(@PathVariable ("hotelId") Long hotelId, @PathVariable ("departmentId") Long departmentId) {
        return touchPointRepository.findTouchPointByHotelIdAndHotelDepartmentId(hotelId, departmentId);
    }


}
