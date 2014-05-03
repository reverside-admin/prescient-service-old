package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class ContactListGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    ContactList contactList;

    @ManyToOne
    Guest guest;
}
