package za.co.prescient.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String name;

    public Hotel(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Hotel()
    {

    }

}
