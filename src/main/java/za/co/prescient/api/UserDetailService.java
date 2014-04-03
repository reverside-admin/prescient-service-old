package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.*;
import za.co.prescient.repository.TouchPointRepository;
import za.co.prescient.repository.UserDetailRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserDetailService {
    private static final Logger LOGGER = Logger.getLogger(UserDetailService.class);

    @Autowired
    UserDetailRepository userDetailRepository;

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


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDetail user) {
        LOGGER.info("request received to create user : " + user);
        user.setPassword("password");
        userDetailRepository.save(user);
    }

    @RequestMapping(value="update/{id}",method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        UserStatus userStatus=new UserStatus();
        userStatus.setId(user.getUserStatus().getId());
        UserType userType=new UserType();
        userType.setId(user.getUserType().getId());
        Hotel hotel=new Hotel();
        hotel.setId(user.getHotel().getId());

        userDetail.setFirstName(user.getFirstName());
        userDetail.setLastName(user.getLastName());
        userDetail.setUserStatus(userStatus);
        userDetail.setUserType(userType);
        userDetail.setHotel(hotel);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value="delete/{id}",method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        UserStatus userStatus = new UserStatus();
        userStatus.setId(Long.valueOf(1));
        userDetail.setUserStatus(userStatus);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value="assignDept/{id}",method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignDept(@PathVariable Long id, @RequestBody List<Department> departments) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setDepartments(departments);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "{userId}/tp/all")
    public List<TouchPoint> getAllDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        List<Department> departments = userDetail.getDepartments();
        LOGGER.info(departments.size());
        List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();
        List<TouchPoint> allTouchPoints = new ArrayList<TouchPoint>();
        for (Department obj : departments) {
            LOGGER.info("Dept  : " + obj.getId() + " " + obj.getName());
            touchPoints = touchPointRepository.findTouchPointByDepartmentId(obj.getId());
            allTouchPoints.addAll(touchPoints);
            LOGGER.info("touchPoints : "+touchPoints);
            LOGGER.info("touchPoints 1 : "+ allTouchPoints);
        }
        LOGGER.info("touchPoints1 : "+ touchPoints);

        return allTouchPoints;
    }

    @RequestMapping(value = "{userId}/tp/notHaving")
    public List<TouchPoint> getNotAllottedTouchpoints(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        List<Department> departments = userDetail.getDepartments();
        LOGGER.info(departments.size());
        List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();
        List<TouchPoint> allTouchPoints = new ArrayList<TouchPoint>();
        for (Department obj : departments) {
            LOGGER.info("Dept  : " + obj.getId() + " " + obj.getName());
            touchPoints = touchPointRepository.findTouchPointByDepartmentId(obj.getId());
            allTouchPoints.addAll(touchPoints);
            LOGGER.info("touchPoints : "+touchPoints);
            LOGGER.info("touchPoints 1 : "+ allTouchPoints);
        }

        List<TouchPoint> superSet = new ArrayList(allTouchPoints);
        List<TouchPoint> subSet = new ArrayList(userDetailRepository.findOne(userId).getTouchPoints());

        superSet.removeAll(subSet);
        for (TouchPoint obj : superSet) {
            LOGGER.info("TP Not Allotted till now : " + obj.getId() + " " + obj.getName());
        }
        return superSet;
    }

    @RequestMapping(value = "{userId}/tp/having")
    public List<TouchPoint> getAllottedTouchpoints(@PathVariable("userId") Long userId) {
        LOGGER.info("Allotted Departments : " + userDetailRepository.findOne(userId).getTouchPoints());
        return userDetailRepository.findOne(userId).getTouchPoints();
    }

    @RequestMapping(value="resetPasswordAdmin/{id}",method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void resetPasswordByAdmin(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setPassword("password");
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value="resetPasswordUser/{id}",method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void resetPasswordByUser(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setPassword(user.getPassword());
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value="assignTP/{id}",method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignTP(@PathVariable Long id, @RequestBody List<TouchPoint> touchPoints) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setTouchPoints(touchPoints);
        userDetailRepository.save(userDetail);
    }


}