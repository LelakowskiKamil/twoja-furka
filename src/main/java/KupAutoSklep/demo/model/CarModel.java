package KupAutoSklep.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_model")
public class CarModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id",referencedColumnName = "id")
    private CarManufacturer manufacturer;

//    @OneToMany(mappedBy = "model_id", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Offer> offers;

    public CarManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(CarManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
