package za.co.prescient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserDetail {

    @Id
    @GeneratedValue
    private Integer Id;

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

}
