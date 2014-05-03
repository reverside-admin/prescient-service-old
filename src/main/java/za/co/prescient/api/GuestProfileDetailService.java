package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Guest;
import za.co.prescient.model.itcs.ItcsTagRead;
import za.co.prescient.repository.CardRepository;
import za.co.prescient.repository.GuestRepository;
import za.co.prescient.repository.itcs.ItcsTagReadRepository;
import za.co.prescient.repository.UserRepository;

import java.util.List;

@RestController
@Slf4j
public class GuestProfileDetailService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ItcsTagReadRepository itcsTagReadRepository;

    @Autowired
    CardRepository guestCardAllocationRepository;

    @RequestMapping(value = "users/{userId}/guestList")
    public List<Guest> getAllGuest(@PathVariable("userId") Long userId) {
        log.info("Allotted Departments : " + userRepository.findOne(userId).getTouchPoints());
        userRepository.findOne(userId).getHotel().getId();
        guestRepository.findByHotelId(userRepository.findOne(userId).getHotel().getId());
        log.info("GUEST LIST" + guestRepository.findByHotelId(userRepository.findOne(userId).getHotel().getId()));
        return guestRepository.findByHotelId(userRepository.findOne(userId).getHotel().getId());
    }

    @RequestMapping(value = "touchpoints/{touchpointId}/guestCards")
    public List<Guest> getGuestIdsByZoneId(@PathVariable("touchpointId") Integer zoneId) {

        List<ItcsTagRead> itcsTagReadList = itcsTagReadRepository.findTagsInZone(zoneId);
        // TODO : List<Guest> guests = guestCardAllocationRepository.findGuestsWithTagsInAZone(itcsTagReadList);
//        for (Guest obj : guests) {
//            log.info("First Name : " + obj.getFirstName());
//        }
//        log.info("List : " + itcsTagReadList);
//        return guests;
        return null;
    }

    @RequestMapping(value="api/guests")
    public List<Guest> findGuests()
    {
        return guestRepository.findAll();
    }


}
