package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.GuestContactListGuest;
import za.co.prescient.model.GuestContactListHeader;
import za.co.prescient.model.GuestContactListTouchPoint;
import za.co.prescient.model.UserDetail;
import za.co.prescient.repository.GuestContactListGuestRepository;
import za.co.prescient.repository.GuestContactListHeaderRepository;
import za.co.prescient.repository.GuestContactListTouchPointRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class GuestContactService {

    @Autowired
    GuestContactListHeaderRepository guestContactListHeaderRepository;

    @Autowired
    GuestContactListTouchPointRepository guestContactListTouchPointRepository;

    @Autowired
    GuestContactListGuestRepository guestContactListGuestRepository;


    @RequestMapping(value = "api/guest/contacts")
    public List<GuestContactListHeader> getContacts() {
        log.info("Get All guest contact List service");
        List<GuestContactListHeader> contacts = guestContactListHeaderRepository.findAll();
        return contacts;
    }


    @RequestMapping(value = "api/guest/contact/create", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)

    public void createContact(@RequestBody GuestContactListHeader guestContactListHeader) {
        log.info("Get All guest contact List service");
        /*GuestContactListTouchPoint tp=new GuestContactListTouchPoint();
        tp.setId(111L);
        List tplist=new ArrayList();
        tplist.add(tp);*/
        //guestContactListHeader.setGuestContactListTouchPoint(tplist);
         guestContactListHeaderRepository.save(guestContactListHeader);
    }

    @RequestMapping(value="api/manager/contacts/touchpoints")
   public List<GuestContactListTouchPoint> findTouchPoints()
   {
       log.info("Get All guest contact List touch point service");
       return  guestContactListTouchPointRepository.findAll();
   }

    @RequestMapping(value="api/manager/contacts/guests")
    public List<GuestContactListGuest> findGuests()
    {
        log.info("Get All guest contact List guest service");
        return  guestContactListGuestRepository.findAll();
    }

}
