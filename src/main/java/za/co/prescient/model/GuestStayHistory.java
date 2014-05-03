package za.co.prescient.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class GuestStayHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    Guest guest;

    @Column
    String roomId;

    @Column
    Date arrivalTime;

    @Column
    Date departureTime;

    @Column
    Boolean currentStayIndicator;

    @ManyToOne
    Hotel hotel;

}
