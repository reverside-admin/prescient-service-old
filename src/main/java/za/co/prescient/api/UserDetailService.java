package za.co.prescient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.UserDetail;
import za.co.prescient.model.UserStatus;
import za.co.prescient.model.UserType;
import za.co.prescient.repository.UserDetailRepository;
import za.co.prescient.repository.UserStatusRepository;
import za.co.prescient.repository.UserTypeRepository;

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



}
