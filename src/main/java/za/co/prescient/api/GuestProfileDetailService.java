package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Guest;
import za.co.prescient.model.GuestCard;
import za.co.prescient.repository.GuestCardRepository;
import za.co.prescient.repository.GuestRepository;
import za.co.prescient.repository.UserRepository;
import za.co.prescient.repository.itcs.ItcsTagReadRepository;

import java.util.ArrayList;
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
    GuestCardRepository guestCardRepository;

    @RequestMapping(value = "api/users/{userId}/guestList")
    public List<Guest> getAllGuest(@PathVariable("userId") Long userId) {
        log.info("Allotted Departments : " + userRepository.findOne(userId).getTouchPoints());
        userRepository.findOne(userId).getHotel().getId();
        guestRepository.findByHotelId(userRepository.findOne(userId).getHotel().getId());
        log.info("GUEST LIST" + guestRepository.findByHotelId(userRepository.findOne(userId).getHotel().getId()));
        return guestRepository.findByHotelId(userRepository.findOne(userId).getHotel().getId());
    }

    @RequestMapping(value = "api/touchpoints/{touchpointId}/guestCards")
    public List<GuestCard> findCurrentlyPresentGuestCardsInTouchPoint(@PathVariable("touchpointId") Long touchPointId) {
        List<Integer> cardIdListInteger = itcsTagReadRepository.findCurrentCardsInZone(touchPointId.intValue());
        log.info("list size : " + cardIdListInteger.size());
        List<Long> cardIdListLong  = new ArrayList<Long>();
        for(Integer cardIdInteger : cardIdListInteger){
           cardIdListLong.add(cardIdInteger.longValue());
        }
        List<GuestCard> guestCardList = guestCardRepository.findByCardIdListWithStatusActive(cardIdListLong);
        return guestCardList;
    }

    @RequestMapping(value="api/guests")
    public List<Guest> findGuests()
    {
        return guestRepository.findAll();
    }


}
