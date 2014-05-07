package za.co.prescient.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "guest_stay_history")
@Data
public class GuestStayHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    Guest guest;

    @Column(name = "room_id")
    String roomId;

    @Column(name = "arrival_time")
    Date arrivalTime;

    @Column(name = "departure_time")
    Date departureTime;

    @Column(name = "current_stay_indicator")
    Boolean currentStayIndicator;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

}
