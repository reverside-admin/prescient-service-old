package za.co.prescient.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contact_list")
@Data
// TODO : Make owner and name combination as unique key
public class ContactList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "owner")
    User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactList")
    List<ContactListTouchPoint> contactListTouchPoints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactList")
    List<ContactListGuest> contactListGuests;

}
