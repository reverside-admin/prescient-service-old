package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ContactListTouchPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    ContactList contactList;

    @ManyToOne
    TouchPoint touchPoint;
}