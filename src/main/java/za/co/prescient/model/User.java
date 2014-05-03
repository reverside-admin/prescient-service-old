package za.co.prescient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @Column//(unique = true)
    String userName;

    @JsonIgnore
    @Column//(nullable = false)
    String password;

    @Column//(nullable = false)
    String firstName;

    @Column//(nullable = false)
    String lastName;

    @ManyToOne//(optional = false)
    UserStatus userStatus;

    @ManyToOne//(optional = false)
    UserType userType;

    @OneToOne//(optional = false)
    Hotel hotel;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "did"))
    List<Department> departments;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "tid"))
    List<TouchPoint> touchPoints;
}
