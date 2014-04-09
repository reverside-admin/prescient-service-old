package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GuestCardAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer guestCardId;

    @ManyToOne
    private GuestProfileDetail guestProfileDetail ;

}
