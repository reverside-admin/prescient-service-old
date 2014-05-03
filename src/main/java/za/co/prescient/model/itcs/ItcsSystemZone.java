package za.co.prescient.model.itcs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class ItcsSystemZone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer zoneId;

    @Column
    private String zoneName;

}
