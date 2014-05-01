package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter@Setter
@ToString
// TODO : Rename the class Guest
public class GuestProfileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Hotel hotel;

    @Column
    private String passportNumber;

    @Column
    private String firstName;

    @Column
    private String preferredName;

    @Column
    private String surname;

    @Column
    private String gender;

    @Column
    private String title;

    @Column
    private String nationalityId;

    @Column
    private Date dob;


}