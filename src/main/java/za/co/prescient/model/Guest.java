package za.co.prescient.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
// TODO : Make it unique (add hotel id to constraint if necessary) (confirm from business)
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String passportNumber;

    @Column
    String firstName;

    @Column
    String preferredName;

    @Column
    String surname;

    @Column
    String gender;

    @Column
    String title;

    @Column
    String nationalityId;

    @Column
    Date dob;

    @ManyToOne
    Hotel hotel;


}