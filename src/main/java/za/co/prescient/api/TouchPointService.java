package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Department;
import za.co.prescient.model.TouchPoint;
import za.co.prescient.model.UserDetail;
import za.co.prescient.repository.TouchPointRepository;
import za.co.prescient.repository.UserDetailRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TouchPointService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private TouchPointRepository touchPointRepository;


    @RequestMapping(value = "api/login/touchpoints", method = RequestMethod.GET, produces = "application/json")
    public List<TouchPoint> getTouchPointsAssignedToLoggedInUser(Principal principal) {
        List<TouchPoint> touchPoints = userDetailRepository.findByUserName(principal.getName()).getTouchPoints();
        return touchPoints;
    }

    @RequestMapping(value = "api/departments/{departmentId}/touchpoints")
    public List<TouchPoint> getTouchPointsOfDepartment(@PathVariable("departmentId") Long departmentId) {
        log.info("Get All Touch Points by DepartmentId service");
        return touchPointRepository.findTouchPointByDepartmentId(departmentId);
    }

}
