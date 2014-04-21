package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;
import za.co.prescient.model.*;
import za.co.prescient.repository.UserDetailRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @RequestMapping(value = "api/users", method = RequestMethod.GET, produces = "application/json")
    public List<UserDetail> get() {
        log.info("Get All UserDetails service");
        return userDetailRepository.findAll();
    }

    @RequestMapping(value = "api/users/{userId}", method = RequestMethod.GET, produces = "application/json")
    public UserDetail get(@PathVariable("userId") Long userId) {
        log.info("Get a single UserDetail service");
        return userDetailRepository.findOne(userId);
    }

    @RequestMapping(value = "api/users/{id}/update/status/{status}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void updateStatus(@PathVariable("id") Long id, @PathVariable("status") Boolean status, Principal principal) {
        log.info("change status service");
        UserDetail userDetail = userDetailRepository.findOne(id);
        if (userDetail.getUserName() == (principal.getName())) {
            throw new RuntimeException("User Can't delete himself");
        } else {
            userDetail.getUserStatus().setId(status ? 0L : 1L);
            userDetailRepository.save(userDetail);
        }
    }

    @RequestMapping(value = "api/users/{id}/reset/password", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable("id") Long id) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setPassword("password");
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDetail user) {
        log.info("request received to create user : " + user);
        user.setPassword("password");
        userDetailRepository.save(user);
    }


}
