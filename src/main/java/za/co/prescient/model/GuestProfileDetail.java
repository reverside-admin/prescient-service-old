package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter@Setter
@ToString
public class GuestProfileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Hotel hotel;

//    @Column
//    private Long operaProfileId;
//
//    @Column
//    private String idNumber;
//
//    @Column
//    private String passportNumber;

    @Column
    private String firstName;

//    @Column
//    private String preferredName;
//
//    @Column
//    private String surname;
//
//    @Column
//    private String gender;
//
//    @Column
//    private String title;
//
//    @Column
//    private String nationalityId;
//
//    @Column
//    private Date dob;
//
//    @Column
//    private Date lastUpdateDate;
//
//    @Column
//    private String photoImagePath;

}