package za.co.prescient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.repository.UserDetailRepository;
import za.co.prescient.model.UserDetail;

import java.util.List;

@RestController
@RequestMapping(value = "service")
public class UserDetailService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @RequestMapping(value = "user/{userName}", method = RequestMethod.GET)
    public UserDetail getUserDetail(@PathVariable("userName") String userName) {
        System.out.println("login service is called.");
        UserDetail userDetail = userDetailRepository.findByUserName(userName);
        return userDetail;
    }
}
