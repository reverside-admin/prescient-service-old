package za.co.prescient.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "guest")
@Data
// TODO : Make it unique (add hotel id to constraint if necessary) (confirm from business)
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "passport_number")
    String passportNumber;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "preferred_name")
    String preferredName;

    @Column(name = "surname")
    String surname;

    @Column(name = "gender")
    String gender;

    @Column(name = "title")
    String title;

    @Column(name = "nationality_id")
    String nationalityId;

    @Column(name = "dob")
    Date dob;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
}