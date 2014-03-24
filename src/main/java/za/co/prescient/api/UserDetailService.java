package za.co.prescient.api;

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
        UserDetail userDetail = userDetailRepository.findByUserName(userName);
        return userDetail;
    }

    @RequestMapping
    public List<UserDetail> get() {
         return userDetailRepository.findAll();
    }

    @RequestMapping(value = "{userId}")
    public UserDetail get(@PathVariable("userId") Long userId) {
        return userDetailRepository.findOne(userId);
    }

    @RequestMapping(value = "/roles")
    public List<UserType> getUserRoles() {
        return userTypeRepository.findAll();
    }

    @RequestMapping(value = "/status")
    public List<UserStatus> getUserStatus() {
        return userStatusRepository.findAll();
    }

    @RequestMapping(value = "/hotel/{hotelId}/departments")
    public Iterable<HotelDepartment> departments(@PathVariable ("hotelId") Long hotelId) {
        return hotelDepartmentRepository.findHotelDepartmentByHotelId(hotelId);
    }

    @RequestMapping(value = "/{departmentId}/touchpoints")
    public Iterable<TouchPoint> touchpoints(@PathVariable ("departmentId") Long departmentId) {
        return touchPointRepository.findTouchPointByHotelDepartmentId(departmentId);
    }

    @RequestMapping(value = "/{hotelId}/{departmentId}/touchpoints")
    public Iterable<TouchPoint> touchpoints(@PathVariable ("hotelId") Long hotelId, @PathVariable ("departmentId") Long departmentId) {
        return touchPointRepository.findTouchPointByHotelIdAndHotelDepartmentId(hotelId, departmentId);
    }


}
