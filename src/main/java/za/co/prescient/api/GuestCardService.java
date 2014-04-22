package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.GuestCard;
import za.co.prescient.repository.GuestCardRepository;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@Slf4j
public class GuestCardService {

    @Autowired
    GuestCardRepository guestCardRepository;

    @RequestMapping(value = "api/guestcards/{msn}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("msn") String msn, @RequestBody GuestCard guestCard) {

        GuestCard gCard = guestCardRepository.findAGuestCard(msn);
        log.info("guestcard1 : " + gCard);
        gCard.setRfidTagNo(guestCard.getRfidTagNo());
        guestCardRepository.save(gCard);
    }

    @RequestMapping(value = "api/guestcards/{msn}/detail")
    public GuestCard get(@PathVariable("msn") String msn) {
        return guestCardRepository.findAGuestCard(msn);
    }

    @RequestMapping(value = "api/guestcards", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void importCards(@RequestBody LinkedHashMap linkedHashMap) {
        log.info("file data::" + linkedHashMap.get("fileData"));
        String str = linkedHashMap.get("fileData").toString();
        log.info("str::" + str);
        String resultStr[] = str.split("\n");
        log.info("No of cards::" + resultStr.length);
        GuestCard guestCard;
        for(String obj:resultStr)
        {
            guestCard=new GuestCard();
            guestCard.setMagStripeNo(obj.trim());
            guestCardRepository.save(guestCard);
        }
    }

    @RequestMapping(value = "api/guestcards/all")
    public List<GuestCard> getall() {
        return guestCardRepository.findAll();
    }
}
