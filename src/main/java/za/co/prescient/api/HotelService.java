package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Department;
import za.co.prescient.model.Hotel;
import za.co.prescient.model.UserDetail;
import za.co.prescient.repository.DepartmentRepository;
import za.co.prescient.repository.HotelRepository;
import za.co.prescient.repository.UserDetailRepository;

import java.util.List;

@RestController
@RequestMapping(value = "api/hotels")
public class HotelService {
    private static final Logger LOGGER = Logger.getLogger(HotelService.class);

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @RequestMapping
    public List<Hotel> get() {
        LOGGER.info("Get All Hotel service");
        return hotelRepository.findAll();
    }

    @RequestMapping(value = "{hotelId}")
    public Hotel get(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Get a single Hotel by HotelId service");
        return hotelRepository.findOne(hotelId);
    }

    @RequestMapping(value = "{hotelId}/departments")
    public List<Department> getDepartments(@PathVariable ("hotelId") Long hotelId) {
        LOGGER.info("Get All Departments by HotelId service");
        return departmentRepository.findByHotelId(hotelId);
    }

    @RequestMapping(value = "{userId}/dept/notHaving")
    public List<Department> getAllDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        LOGGER.info("Hotel Name : "+userDetail.getHotel().getName());
        return departmentRepository.findByHotelId(userDetail.getHotel().getId());
    }

    @RequestMapping(value = "{userId}/dept/having")
    public List<Department> getAllottedDepartments(@PathVariable("userId") Long userId) {
        LOGGER.info("Allotted Departments : " + userDetailRepository.findOne(userId).getDepartment());
        return userDetailRepository.findOne(userId).getDepartment();
    }

}
