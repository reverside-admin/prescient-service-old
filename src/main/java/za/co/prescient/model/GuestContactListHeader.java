package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
// TODO : Make owner and name combination as unique key
public class GuestContactListHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    // TODO : Change the name to name
    @Column
    private String guestContactListName;

    // TODO : refer to an user object
    private Long ownerId;


    // TODO : remove joinColumn and add mappedBy -> contactList
    @OneToMany(cascade = CascadeType.ALL,targetEntity=GuestContactListTouchPoint.class)
    @JoinColumn(name="contact_list_id", referencedColumnName="id")
    List<GuestContactListTouchPoint> guestContactListTouchPoint;



    @OneToMany(cascade = CascadeType.ALL,targetEntity=GuestContactListGuest.class)
    @JoinColumn(name="contact_list_id", referencedColumnName="id")
    List<GuestContactListGuest> guestContactListGuest;


   /* @OneToMany(mappedBy = "guestContactListHeader",cascade = CascadeType.ALL,targetEntity=GuestContactListTouchPoint.class)
    List<GuestContactListTouchPoint> guestContactListTouchPoint;*/






}
