package za.co.prescient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String userName;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne
    private UserStatus userStatus;

    @ManyToOne
    private UserType userType;

    @OneToOne
    private Hotel hotel;

    @OneToMany
    @JoinTable( joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "did"))
    private List<Department> department;
}
