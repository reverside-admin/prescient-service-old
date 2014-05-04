package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.*;
import za.co.prescient.model.itcs.ItcsTagRead;
import za.co.prescient.model.itcs.ItcsTagReadHistory;
import za.co.prescient.repository.*;
import za.co.prescient.repository.itcs.ItcsTagReadHistoryRepository;
import za.co.prescient.repository.itcs.ItcsTagReadRepository;

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
    UserRepository userRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ItcsTagReadRepository itcsTagReadRepository;

    @Autowired
    GuestCardRepository guestCardRepository;

    @Autowired
    GuestStayHistoryRepository guestStayHistoryRepository;

    @Autowired
    SetupRepository setupRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ItcsTagReadHistoryRepository itcsTagReadHistoryRepository;

    @RequestMapping(value = "users/assignDept/{Userid}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void assignDept(@PathVariable Long id, @RequestBody List<Department> departments) {
        User user = userRepository.findOne(id);
        user.setDepartments(departments);
        userRepository.save(user);
    }

    @RequestMapping(value = "touchpoints/guestCardIds/all")
    public List<ItcsTagRead> getGuestIds() {
        return itcsTagReadRepository.findAll();
    }

    @RequestMapping(value = "guest/{guestId}")
    public GuestStayHistory getGuestDetailByGuestId(@PathVariable("guestId") Long guestId) {
        LOGGER.info("guest detail service is invoked");
        return guestStayHistoryRepository.findByGuest(guestId);
    }


    @RequestMapping(value = "hotels/{hotelId}/guests/checkedIn")
    public List<GuestStayHistory> getAllCheckedInGuests(@PathVariable("hotelId") Long hotelId) {

        LOGGER.info("find checked in guest list service is invoked");
        return guestStayHistoryRepository.findCheckedInByHotelId(hotelId);
    }


    @RequestMapping(value = "guests/{guestId}/locations")
    public ItcsTagRead getGuestCardHistory(@PathVariable("guestId") Long guestId) {
        LOGGER.info("guestcard by guest id service is called");
         GuestCard guestCardAllocation = guestCardRepository.findGuestCardByGuestId(guestId);
        LOGGER.info("guest card---------------"+guestCardAllocation);
        ItcsTagRead itc=itcsTagReadRepository.findGuestCardHistory(guestCardAllocation.getCard().getId().intValue());
        return itc;
    }


    @RequestMapping(value = "guests/{guestId}/location/history")
    public List<ItcsTagReadHistory> getGuestHistory(@PathVariable("guestId") Long guestId) {
        LOGGER.info("guestcard history service is called");
         GuestCard guestCardAllocation = guestCardRepository.findGuestCardByGuestId(guestId);

        List<ItcsTagReadHistory> itc= itcsTagReadHistoryRepository.findGuestHistory(guestCardAllocation.getCard().getId().intValue());

        LOGGER.info("return list size::"+itc.size());
        return itc;
    }

}
