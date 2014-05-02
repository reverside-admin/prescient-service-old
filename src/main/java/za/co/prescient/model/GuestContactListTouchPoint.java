package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GuestContactListTouchPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private GuestContactListHeader guestContactListHeader;

    // TODO : Refer to touchPoint table
    private Integer touchPointId;


    /*@ManyToOne
    @JoinColumn(name="contact_list_id")
    GuestContactListHeader guestContactListHeader;
*/

}
