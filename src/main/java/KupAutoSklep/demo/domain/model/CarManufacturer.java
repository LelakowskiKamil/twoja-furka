package KupAutoSklep.demo.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "car_manufacturer")
public class CarManufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public CarManufacturer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarManufacturer() {
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
