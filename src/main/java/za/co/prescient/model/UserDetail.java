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
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column//(unique = true)
    private String userName;

    @JsonIgnore
    @Column//(nullable = false)
    private String password;

    @Column//(nullable = false)
    private String firstName;

    @Column//(nullable = false)
    private String lastName;

    @ManyToOne//(optional = false)
    private UserStatus userStatus;

    @ManyToOne//(optional = false)
    private UserType userType;

    @OneToOne//(optional = false)
    private Hotel hotel;

    @ManyToMany
    @JoinTable( joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "did"))
    private List<Department> departments;

    @ManyToMany
    @JoinTable( joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "tid"))
    private List<TouchPoint> touchPoints;
}
