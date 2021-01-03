package KupAutoSklep.demo.domain.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 255, min = 5)
    @Column(name = "title")
    private String title;

    @NotNull
    @Min(1900)
    @Column(name = "year")
    private Integer year;

    @NotNull
    @Min(0)
    @Column(name = "mileage")
    private Integer mileage;


    @Column(name = "engine_size")
    @Min(0)
    @Max(100)
    private BigDecimal engineSize;

    @Column(name = "engine_power")
    @Min(0)
    private Integer enginePower;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "doors")
    private Integer doors;

    @NotNull
    @Size(max = 30, min = 3)
    @Column(name = "colour")
    private String colour;

    @NotNull
    @Lob
    @Size(max = 65535, min = 5)
    @Column(name = "description")
    private String description;

    @NotNull
    @Min(0)
    @Column(name = "price")
    private Integer price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private CarModel model;

    @NotNull
    @JoinColumn(name = "body_style_id", referencedColumnName = "id")
    @ManyToOne
    private BodyStyle bodyStyle;

    @NotNull
    @JoinColumn(name = "fuel_type_id", referencedColumnName = "id")
    @ManyToOne
    private FuelType fuelType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(BigDecimal engineSize) {
        this.engineSize = engineSize;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}
