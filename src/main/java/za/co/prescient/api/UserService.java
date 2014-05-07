package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.User;
import za.co.prescient.repository.local.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "api/users", method = RequestMethod.GET, produces = "application/json")
    public List<User> get() {
        log.info("Get All UserDetails service");
        return userRepository.findAll();
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        LOGGER.info("request received to create user : " + user);
        user.setPassword("password");
        userRepository.save(user);
    }

    @RequestMapping(value = "api/users/{userId}", method = RequestMethod.GET, produces = "application/json")
    public User get(@PathVariable("userId") Long userId) {
        log.info("Get a single UserDetail service");
        return userRepository.findOne(userId);
    }

    @RequestMapping(value = "api/users/{id}/update/status/{status}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void updateStatus(@PathVariable("id") Long id, @PathVariable("status")boolean status, Principal principal) {
        User user = userRepository.findOne(id);
        if (user.getUserName() == (principal.getName())) {
            throw new RuntimeException("User Can't delete himself");
        } else {
           //user.getUserStatus().setId(status ? 1L : 2L);
            //user.getUserStatus().setValue("updated");
            log.info("status"+user.getUserStatus().getValue());
            userRepository.save(user);
        }
    }

    @RequestMapping(value = "api/users/{id}/reset/password", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable("id") Long id) {
        User user = userRepository.findOne(id);
        user.setPassword("password");
        userRepository.save(user);
    }

}
