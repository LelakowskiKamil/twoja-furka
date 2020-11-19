package KupAutoSklep.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fuel_type")
public class FuelType {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fuelType")
    private List<Offer> offers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
