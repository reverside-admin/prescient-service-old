package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.*;
import za.co.prescient.repository.*;

import java.security.Principal;
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

    @Autowired
    ItcsTagReadHistoryRepository itcsTagReadHistoryRepository;

    @RequestMapping(value = "users/assignDept/{Userid}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignDept(@PathVariable Long id, @RequestBody List<Department> departments) {
        UserDetail userDetail = userDetailRepository.findOne(id);
        userDetail.setDepartments(departments);
        userDetailRepository.save(userDetail);
    }

    @RequestMapping(value = "touchpoints/guestCardIds/all")
    public List<ItcsTagRead> getGuestIds() {
        return itcsTagReadRepository.findAll();
    }

    @RequestMapping(value = "guest/{guestId}")
    public GuestStayDetail getGuestDetailByGuestId(@PathVariable("guestId") Integer guestId) {
        LOGGER.info("guest detail service is invoked");
        return guestStayDetailRepository.findGuestDetailByGId(guestId);
    }


    @RequestMapping(value = "hotels/{hotelId}/guests/checkedIn")
    public List<GuestStayDetail> getAllCheckedInGuests(@PathVariable("hotelId") Long hotelId) {

        LOGGER.info("find checked in guest list service is invoked");
        return guestStayDetailRepository.findAllCheckedInGuests(hotelId);
    }

    //find guest list by touchpoint
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

//   /* @RequestMapping(value = "guests/{guestId}/guestCard")
//    public GuestCardAllocation getGuestCard(@PathVariable("guestId") Long guestId) {
//        LOGGER.info("guestcard by guest id service is called");
//      return guestCardAllocationRepository.findGuestCardByGuestId(guestId);
//    }*/



    @RequestMapping(value = "guests/{guestId}/locations")
    public ItcsTagRead getGuestCardHistory(@PathVariable("guestId") Long guestId) {
        LOGGER.info("guestcard by guest id service is called");
         GuestCardAllocation guestCardAllocation = guestCardAllocationRepository.findGuestCardByGuestId(guestId);
//        List<ItcsTagRead> itcList=itcsTagReadRepository.findGuestCardHistory(guestCardAllocation.getGuestCardId());
//        LOGGER.info("return list size::"+itcList.size());

        ItcsTagRead itc=itcsTagReadRepository.findGuestCardHistory(guestCardAllocation.getGuestCardId());
       // LOGGER.info("return list size::"+itc.getId());
        return itc;
    }


    @RequestMapping(value = "guests/{guestId}/location/history")
    public List<ItcsTagReadHistory> getGuestHistory(@PathVariable("guestId") Long guestId) {
        LOGGER.info("guestcard history service is called");
         GuestCardAllocation guestCardAllocation = guestCardAllocationRepository.findGuestCardByGuestId(guestId);

        List<ItcsTagReadHistory> itc= itcsTagReadHistoryRepository.findGuestHistory(guestCardAllocation.getGuestCardId());

        LOGGER.info("return list size::"+itc.size());
        return itc;
    }







}
