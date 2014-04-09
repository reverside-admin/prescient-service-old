package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.UserDetail;
import za.co.prescient.repository.UserDetailRepository;

import java.security.Principal;

@RestController
@Slf4j
public class LoginService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @RequestMapping(value = "login/{userName}/{password}", produces = "application/json")
    @ResponseBody
    public UserDetail login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        log.info("User Login -> userName:{}, password:{}", userName, password);
        UserDetail userDetail = userDetailRepository.findByUserNameAndPassword(userName, password);
        if (userDetail == null) {
            throw new RuntimeException("User Not Found");
        }
        return userDetail;
    }


    @RequestMapping(value = "api/login/reset/password/{newPassword}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@PathVariable("newPassword")String newPassword, Principal principal) {
        String userName = principal.getName();
        UserDetail userDetail = userDetailRepository.findByUserName(userName);
        userDetail.setPassword(newPassword);
        userDetailRepository.save(userDetail);
    }
}
