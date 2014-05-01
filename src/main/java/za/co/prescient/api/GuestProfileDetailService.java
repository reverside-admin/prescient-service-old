package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.GuestProfileDetail;
import za.co.prescient.model.ItcsTagRead;
import za.co.prescient.repository.GuestCardAllocationRepository;
import za.co.prescient.repository.GuestProfileDetailRepository;
import za.co.prescient.repository.ItcsTagReadRepository;
import za.co.prescient.repository.UserDetailRepository;

import java.util.List;

@RestController
@Slf4j
public class GuestProfileDetailService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    GuestProfileDetailRepository guestProfileDetailRepository;

    @Autowired
    ItcsTagReadRepository itcsTagReadRepository;

    @Autowired
    GuestCardAllocationRepository guestCardAllocationRepository;

    @RequestMapping(value = "users/{userId}/guestList")
    public List<GuestProfileDetail> getAllGuest(@PathVariable("userId") Long userId) {
        log.info("Allotted Departments : " + userDetailRepository.findOne(userId).getTouchPoints());
        userDetailRepository.findOne(userId).getHotel().getId();
        guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId());
        log.info("GUEST LIST" + guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId()));
        return guestProfileDetailRepository.findGuestProfileDetailByHotelId(userDetailRepository.findOne(userId).getHotel().getId());
    }

    @RequestMapping(value = "touchpoints/{touchpointId}/guestCards")
    public List<GuestProfileDetail> getGuestIdsByZoneId(@PathVariable("touchpointId") Integer zoneId) {

        List<ItcsTagRead> itcsTagReadList = itcsTagReadRepository.findTagsInZone(zoneId);
        List<GuestProfileDetail> guestProfileDetails = guestCardAllocationRepository.findGuestsWithTagsInAZone(itcsTagReadList);
        for (GuestProfileDetail obj : guestProfileDetails) {
            log.info("First Name : " + obj.getFirstName());
        }
        log.info("List : " + itcsTagReadList);
        return guestProfileDetails;
    }

    @RequestMapping(value="api/guests")
    public List<GuestProfileDetail> findGuests()
    {
        return guestProfileDetailRepository.findAll();
    }


}
