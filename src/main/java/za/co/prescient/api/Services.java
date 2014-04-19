package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.*;
import za.co.prescient.repository.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class Services {
    private static final Logger LOGGER = Logger.getLogger(Services.class);

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TouchPointRepository touchPointRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    GuestProfileDetailRepository guestProfileDetailRepository;

    @Autowired
    ItcsTagReadRepository itcsTagReadRepository;

    @Autowired
    GuestCardAllocationRepository guestCardAllocationRepository;

    @Autowired
    GuestStayDetailRepository guestStayDetailRepository;

    @Autowired
    TouchPointSetupRepository touchPointSetupRepository;

    @Autowired
    GuestCardRepository guestCardRepository;

    @RequestMapping(value = "status")
    public List<UserStatus> getAllUserStatus() {
        LOGGER.info("Get All Status service");
        List<UserStatus> userStatuses = userStatusRepository.findAll();
        return userStatuses;
    }

    @RequestMapping(value = "roles")
    public List<UserType> getAllUserRoles() {
        LOGGER.info("Get All Roles service");
        List<UserType> userTypes = userTypeRepository.findAll();
        return userTypes;
    }

    @RequestMapping(value = "hotels")
    public List<Hotel> getAllHotels() {
        LOGGER.info("Get All Hotel service");
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }

    @RequestMapping(value = "hotels/{hotelId}/departments")
    public List<Department> getDepartments(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Get All Departments by HotelId service");
        List<Department> departments = departmentRepository.findByHotelId(hotelId);
        return departments;
    }

    @RequestMapping(value = "departments/tp/all", method = RequestMethod.POST, consumes = "application/json")
    public List<TouchPoint> getAllTouchPointsByDepts(@RequestBody List<Department> departments) {
        LOGGER.info("Get All TPs by DeptIDs");
        List<TouchPoint> touchPoints;
        List<TouchPoint> allTouchPoints = new ArrayList<TouchPoint>();
        for (Department obj : departments) {
            touchPoints = touchPointRepository.findTouchPointByDepartmentId(obj.getId());
            allTouchPoints.addAll(touchPoints);
        }
        return allTouchPoints;
    }

    @RequestMapping(value = "users/assignDept/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignDept(@PathVariable Long id, @RequestBody List<Department> departments) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setDepartments(departments);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "users/{userId}/guestList")
    public List<GuestProfileDetail> getAllGuest(@PathVariable("userId") Long userId) {
        LOGGER.info("Allotted Departments : " + userDetailRepository.findOne(userId).getTouchPoints());
        userDetailRepository.findOne(userId).getHotel().getId();
        guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId());
        LOGGER.info("GUEST LIST" + guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId()));
        return guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId());
    }


    @RequestMapping(value = "hotels/{hotelId}")
    public Hotel getAHotel(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Get a single Hotel by HotelId service");
        return hotelRepository.findOne(hotelId);
    }


    @RequestMapping(value = "hotels/{userId}/dept/all")
    public List<Department> getAllDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        return departmentRepository.findByHotelId(userDetail.getHotel().getId());
    }

    @RequestMapping(value = "hotels/{userId}/dept/notHaving")
    public List<Department> getNotAllottedDepartments(@PathVariable("userId") Long userId) {
        UserDetail userDetail = userDetailRepository.findOne(userId);
        List<Department> superSet = new ArrayList(departmentRepository.findByHotelId(userDetail.getHotel().getId()));
        List<Department> subSet = new ArrayList(userDetailRepository.findOne(userId).getDepartments());
        superSet.removeAll(subSet);
        for (Department obj : superSet) {
            LOGGER.info("Dept Not Allotted till now : " + obj.getId() + " " + obj.getName());
        }
        return superSet;
    }

    @RequestMapping(value = "hotels/{userId}/dept/having")
    public List<Department> getAllottedDepartments(@PathVariable("userId") Long userId) {
        LOGGER.info("Allotted Departments : " + userDetailRepository.findOne(userId).getDepartments());
        return userDetailRepository.findOne(userId).getDepartments();
    }

    @RequestMapping(value = "touchpoints/guestCardIds/all")
    public List<ItcsTagRead> getGuestIds() {
        return itcsTagReadRepository.findAll();
    }

    @RequestMapping(value = "touchpoints/{tpId}/guestCards")
    public List<GuestProfileDetail> getGuestIdsByZoneId(@PathVariable("tpId") Integer zoneId) {

        List<ItcsTagRead> itcsTagReadList = itcsTagReadRepository.findTagsInZone(zoneId);
        List<GuestProfileDetail> guestProfileDetails = guestCardAllocationRepository.findGuestsWithTagsInAZone(itcsTagReadList);
        for (GuestProfileDetail obj : guestProfileDetails) {
            LOGGER.info("First Name : " + obj.getFirstName());
        }
        LOGGER.info("List : " + itcsTagReadList);
        return guestProfileDetails;
    }


    @RequestMapping(value = "guest/{guestId}")
    public GuestStayDetail getGuestDetailByGuestId(@PathVariable("guestId") Integer guestId) {
        LOGGER.info("guest detail service is invoked");
        return guestStayDetailRepository.findGuestDetailByGId(guestId);
    }


    @RequestMapping(value = "tp/setup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TouchPointSetup touchPointSetup) {
        touchPointSetupRepository.save(touchPointSetup);
    }


    @RequestMapping(value = "tp/{tpid}/setups")
    public List<TouchPointSetup> getAll(@PathVariable("tpid") Long tpid) {
        return touchPointSetupRepository.findTpSetupsByTpId(tpid);
    }

    @RequestMapping(value = "tpsetup/{setupId}")
    public TouchPointSetup getDetail(@PathVariable("setupId") Long id) {
        return touchPointSetupRepository.findOne(id);
    }

    @RequestMapping(value = "tpsetup/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody TouchPointSetup touchPointSetup) {

        TouchPointSetup tpSetup = touchPointSetupRepository.findOne(id);

        tpSetup.setSetupName(touchPointSetup.getSetupName());
        tpSetup.setSetupDescription(touchPointSetup.getSetupDescription());
        tpSetup.setImageName(touchPointSetup.getImageName());
        tpSetup.setFileName(touchPointSetup.getFileName());
        tpSetup.setLengthX(touchPointSetup.getLengthX());
        tpSetup.setLengthY(touchPointSetup.getLengthY());
        tpSetup.setFilePath(touchPointSetup.getFilePath());

        touchPointSetupRepository.save(tpSetup);
    }

    @RequestMapping(value = "tpsetup/{id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void delete(@PathVariable("id") Long id) {

        touchPointSetupRepository.delete(id);

    }

    @RequestMapping(value = "guestcards/{msn}/detail")
    public GuestCard get(@PathVariable("msn") String msn) {
        return guestCardRepository.findAGuestCard(msn);
    }


    @RequestMapping(value = "guestcards/{msn}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("msn") String msn, @RequestBody GuestCard guestCard) {

        GuestCard gCard = guestCardRepository.findAGuestCard(msn);
        LOGGER.info("guestcard1 : " + gCard);
        gCard.setRfidTagNo(guestCard.getRfidTagNo());
        guestCardRepository.save(gCard);
    }

    @RequestMapping(value = "guestcards", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void importCards(@RequestBody LinkedHashMap linkedHashMap) {
        LOGGER.info("file data::" + linkedHashMap.get("fileData"));
        String str = linkedHashMap.get("fileData").toString();
        LOGGER.info("str::" + str);
        String resultStr[] = str.split("\n");
        LOGGER.info("No of cards::" + resultStr.length);
        GuestCard guestCard;
        for(String obj:resultStr)
        {
            guestCard=new GuestCard();
            guestCard.setMagStripeNo(obj.trim());
            guestCardRepository.save(guestCard);
        }
    }


    @RequestMapping(value = "guestcards/all")
    public List<GuestCard> getall() {
        return guestCardRepository.findAll();
    }


}
