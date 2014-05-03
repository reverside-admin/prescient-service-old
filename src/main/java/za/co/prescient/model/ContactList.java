package za.co.prescient.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
// TODO : Make owner and name combination as unique key
public class ContactList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;

    @Column
    String name;

    @ManyToOne
    User owner;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = ContactListTouchPoint.class)
    @JoinColumn(name = "contact_list_id", referencedColumnName = "id")
    List<ContactListTouchPoint> contactListTouchPoints;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = ContactListGuest.class)
    @JoinColumn(name = "contact_list_id", referencedColumnName = "id")
    List<ContactListGuest> contactListGuests;

}
