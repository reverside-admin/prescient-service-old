package za.co.prescient.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter@Setter
@ToString
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public Hotel(){

    }

    public Hotel(Long id, String name){
        this.id = id;
        this.name = name;
    }

}
