package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
// TODO : Rename the class name to Card
public class GuestCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO : Make it unique
    @Column
    private String magStripeNo;

    // TODO:  Make it unique
    @Column
    private String rfidTagNo;

}
