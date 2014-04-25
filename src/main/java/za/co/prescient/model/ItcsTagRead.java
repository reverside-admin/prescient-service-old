package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class ItcsTagRead {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer guestCard;

    @Column
    private Integer zone; //Same as Touch Point Id

    @Column
    private Double xCoordRead;

    @Column
    private Double yCoordRead;

    @Column
    private Date tagReadDatetime;


}
