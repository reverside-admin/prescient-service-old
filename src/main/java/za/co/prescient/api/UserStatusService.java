package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.UserStatus;
import za.co.prescient.repository.UserStatusRepository;

@RestController
@RequestMapping(value = "api/users")
public class UserStatusService {

    private static final Logger LOGGER = Logger.getLogger(UserStatusService.class);

    @Autowired
    UserStatusRepository userStatusRepository;

    @RequestMapping(value = "/status")
    public Iterable<UserStatus> getUserStatus() {
        LOGGER.info("Get All Status service");
        return userStatusRepository.findAll();
    }
}
