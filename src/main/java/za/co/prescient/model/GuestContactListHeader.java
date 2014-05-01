package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class GuestContactListHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String guestContactListName;

    private Long ownerId;


    @OneToMany(cascade = CascadeType.ALL,targetEntity=GuestContactListTouchPoint.class)
    @JoinColumn(name="contact_list_id", referencedColumnName="id")
    List<GuestContactListTouchPoint> guestContactListTouchPoint;




}
