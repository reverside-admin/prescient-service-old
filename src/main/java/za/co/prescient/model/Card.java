package za.co.prescient.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    String magStripeNo;

    @Column(unique = true)
    String rfidTagNo;

}
