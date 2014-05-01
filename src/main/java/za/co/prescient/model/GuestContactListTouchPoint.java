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

    private Integer touchPointId;
    /*@ManyToOne
    @JoinColumn
    GuestContactListHeader guestContactListHeader;*/
}
