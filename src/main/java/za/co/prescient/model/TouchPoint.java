package za.co.prescient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "touch_point")
@Data
public class TouchPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "touchPoint")
    List<Setup> setups;
}
