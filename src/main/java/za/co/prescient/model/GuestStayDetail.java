package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class GuestStayDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    String roomId;

    @Column
    Date arrivalTime;

    @Column
    Date departureTime;

    @Column
    Integer guestId;


}
