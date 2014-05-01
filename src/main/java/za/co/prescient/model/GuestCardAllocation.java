package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
// TODO : Rename the class name as GuestCard
// TODO : Make card and guest unique (Confirm from business)
public class GuestCardAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //TODO: make relationship with Card Table and rename it as card
    @Column
    private Integer guestCardId;

    // TODO : Rename it as guest
    @ManyToOne
    private GuestProfileDetail guestProfileDetail ;

}
