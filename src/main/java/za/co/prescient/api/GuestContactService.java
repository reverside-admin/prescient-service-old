package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.ContactList;
import za.co.prescient.model.ContactListGuest;
import za.co.prescient.model.ContactListTouchPoint;
import za.co.prescient.repository.ContactListGuestRepository;
import za.co.prescient.repository.ContactListTouchPointRepository;
import za.co.prescient.repository.ContactListRepository;

import java.util.List;

@RestController
@Slf4j
public class GuestContactService {

    @Autowired
    ContactListRepository contactListRepository;

    @Autowired
    ContactListTouchPointRepository contactListTouchPointRepository;

    @Autowired
    ContactListGuestRepository contactListGuestRepository;


    @RequestMapping(value = "api/guest/contacts")
    public List<ContactList> getContacts() {
        log.info("Get All guest contact List service");
        List<ContactList> contacts = contactListRepository.findAll();
        return contacts;
    }


    @RequestMapping(value = "api/guest/contact/create", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)

    public void createContact(@RequestBody ContactList contactList) {
        log.info("Get All guest contact List service");
        /*GuestContactListTouchPoint tp=new GuestContactListTouchPoint();
        tp.setId(111L);
        List tplist=new ArrayList();
        tplist.add(tp);*/
        //guestContactListHeader.setGuestContactListTouchPoint(tplist);
         contactListRepository.save(contactList);
    }

    @RequestMapping(value="api/manager/contacts/touchpoints")
   public List<ContactListTouchPoint> findTouchPoints()
   {
       log.info("Get All guest contact List touch point service");
       return  contactListTouchPointRepository.findAll();
   }

    @RequestMapping(value="api/manager/contacts/guests")
    public List<ContactListGuest> findGuests()
    {
        log.info("Get All guest contact List guest service");
        return  contactListGuestRepository.findAll();
    }

}
