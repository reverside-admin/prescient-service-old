package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class GuestContactListGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


     private Integer guestId;
}
