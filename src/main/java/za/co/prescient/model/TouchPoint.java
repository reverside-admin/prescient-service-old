package za.co.prescient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter@Setter
public class TouchPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    private Department department;

    @OneToMany
    @JoinColumn(name="touch_point_id", referencedColumnName = "id")
    private List<TouchPointSetup> touchPointSetups;
}
