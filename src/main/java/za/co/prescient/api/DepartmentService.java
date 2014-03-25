package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/hotels")
public class DepartmentService {
    private static final Logger LOGGER = Logger.getLogger(DepartmentService.class);

}
