package za.co.prescient.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class GuestCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    Card card;

    @ManyToOne
    Guest guest;

    @Column
    Date issueDate;

    @Column
    Boolean status;

}
