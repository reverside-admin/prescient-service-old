package za.co.prescient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TouchPointSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    String setupName;

    @Column
    String setupDescription;

    @Column
    Boolean setupIndicator=false;

    @Column
    String imageName;

    @Column
    String fileName;

    @Column
    Double lengthX;

    @Column
    Double lengthY;

    @Column
    String filePath;

    @Column(name="touch_point_id")
    Long touchPointId;

}
