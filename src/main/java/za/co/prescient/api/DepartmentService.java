package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Department;
import za.co.prescient.model.UserDetail;
import za.co.prescient.repository.DepartmentRepository;
import za.co.prescient.repository.UserDetailRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @RequestMapping(value = "api/hotels/{hotelId}/departments")
    public List<Department> getAllDepartmentsByHotel(@PathVariable("hotelId") Long hotelId) {
        log.info("Get All Departments by HotelId service");
        List<Department> departments = departmentRepository.findByHotelId(hotelId);
        return departments;
    }

    @RequestMapping(value = "api/hotels/{userId}/dept/all")
    public List<Department> getAllDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        log.info("Get All Departments by UserId Service");
        return departmentRepository.findByHotelId(userDetail.getHotel().getId());
    }

    @RequestMapping(value = "api/hotels/{userId}/dept/having")
    public List<Department> getAllottedDepartments(@PathVariable("userId") Long userId) {
        log.info("Get All Assigned Departments by UserId List Service");
        return userDetailRepository.findOne(userId).getDepartments();
    }

    @RequestMapping(value = "api/hotels/{userId}/dept/notHaving")
    public List<Department> getNotAllottedDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        List<Department> superSet = new ArrayList(departmentRepository.findByHotelId(userDetail.getHotel().getId()));
        List<Department> subSet = new ArrayList(userDetailRepository.findOne(userId).getDepartments());
        superSet.removeAll(subSet);
        for (Department obj : superSet) {
            log.info("Dept Not Allotted till now : " + obj.getId() + " " + obj.getName());
        }
        log.info("Get All Not Assigned Departments List Service");
        return superSet;
    }

}
