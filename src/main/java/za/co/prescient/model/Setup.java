package za.co.prescient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Setup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    //TODO:name is changed to setupName bcoz form does not bind name attribute bcoz name is a build in property of form
    @Column
    String setupName;

    @Column
    String description;

    @Column
    Boolean indicator;

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

    @ManyToOne

    TouchPoint touchPoint;

}
