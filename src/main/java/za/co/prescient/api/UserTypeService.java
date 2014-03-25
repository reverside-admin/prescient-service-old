package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.UserType;
import za.co.prescient.repository.UserTypeRepository;

@RestController
@RequestMapping(value = "api/users")
public class UserTypeService {

    private static final Logger LOGGER = Logger.getLogger(UserTypeService.class);

    @Autowired
    UserTypeRepository userTypeRepository;

    @RequestMapping(value = "/roles")
    public Iterable<UserType> getUserRoles() {
        LOGGER.info("Get All Roles service");
        return userTypeRepository.findAll();
    }
}
