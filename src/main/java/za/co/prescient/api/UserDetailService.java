package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.UserDetail;
import za.co.prescient.model.UserStatus;
import za.co.prescient.repository.UserDetailRepository;

@RestController
@RequestMapping(value = "api/users")
//@Slf4j
public class UserDetailService {
    private static final Logger LOGGER = Logger.getLogger(UserDetailService.class);

    @Autowired
    UserDetailRepository userDetailRepository;

    @RequestMapping
    public Iterable<UserDetail> get() {
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
        userDetailRepository.save(user);
    }

    @RequestMapping(value="update/{id}",method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable Long id, @RequestBody UserDetail user) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setFirstName(user.getFirstName());
        userDetail.setLastName(user.getLastName());
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

}
