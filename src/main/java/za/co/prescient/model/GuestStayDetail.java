package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
// TODO : Rename the class as GuestStayHistory
public class GuestStayDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //TODO: roomId should have a relational mapping with HOTEL_ROOM
    @Column
    String roomId;

    @Column
    Date arrivalTime;

    @Column
    Date departureTime;

    @Column
    Integer guestId;

    @Column
    Boolean currentStayInd;

    @ManyToOne
    GuestProfileDetail guestProfileDetail;

    //TODO: verify the relationship with Hotel Table
    // TODO : remove the relation to hotel id as reference to guest will have
    @Column
    Long hotelId;

}
