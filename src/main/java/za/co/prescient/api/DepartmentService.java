package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Department;
import za.co.prescient.repository.DepartmentRepository;

@RestController
@RequestMapping(value = "api/hotels")
public class DepartmentService {
    private static final Logger LOGGER = Logger.getLogger(DepartmentService.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(value = "{hotelId}/departments")
    public Iterable<Department> departments(@PathVariable ("hotelId") Long hotelId) {
        LOGGER.info("Get All Departments by HotelId service");
        return departmentRepository.findDepartmentByHotelId(hotelId);
    }
}
