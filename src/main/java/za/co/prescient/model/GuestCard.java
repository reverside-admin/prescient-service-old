package za.co.prescient.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "guest_card")
@Data
public class GuestCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    Guest guest;

    @ManyToOne
    @JoinColumn(name = "card_id")
    Card card;

    @Column(name = "issue_date")
    Date issueDate;

    @Column(name = "status")
    Boolean status;

}
