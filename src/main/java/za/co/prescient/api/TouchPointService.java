package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.TouchPoint;
import za.co.prescient.repository.TouchPointRepository;

@RestController
@RequestMapping(value = "api/departments/")

public class TouchPointService {
    private static final Logger LOGGER = Logger.getLogger(TouchPointService.class);

    @Autowired
    TouchPointRepository touchPointRepository;

    @RequestMapping(value = "{departmentId}/touchpoints")
    public Iterable<TouchPoint> touchpoints(@PathVariable ("departmentId") Long departmentId) {
        LOGGER.info("Get All Touch Points by DepartmentId service");
        return touchPointRepository.findTouchPointByDepartmentId(departmentId);
    }

}
