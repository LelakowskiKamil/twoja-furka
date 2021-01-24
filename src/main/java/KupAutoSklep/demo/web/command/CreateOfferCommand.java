package KupAutoSklep.demo.web.command;

import KupAutoSklep.demo.domain.model.BodyStyle;
import KupAutoSklep.demo.domain.model.CarModel;
import KupAutoSklep.demo.domain.model.FuelType;
import KupAutoSklep.demo.domain.model.login.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateOfferCommand {

    @Size(max = 255, min = 5)
    private String title;

    @Min(1900)
    private Integer year;

    @Min(0)
    private Integer mileage;


    @Min(0)
    private BigDecimal engineSize;

    @Min(0)
    private Integer enginePower;

    @Min(1)
    @Max(5)
    private Integer doors;

    @Size(max = 30, min = 3)
    private String colour;

    @Size(max = 65535, min = 5)
    private String description;

    @Min(0)
    private Integer price;

    private CarModel model;

    private BodyStyle bodyStyle;

    private FuelType fuelType;

    private User user;

    public CreateOfferCommand() {
    }

    public CreateOfferCommand(@Size(max = 255, min = 5) String title, @Min(1900) Integer year, @Min(0) Integer mileage, @Min(0) BigDecimal engineSize, @Min(0) Integer enginePower, @Min(1) @Max(5) Integer doors, @Size(max = 30, min = 3) String colour, @Size(max = 65535, min = 5) String description, @Min(0) Integer price, CarModel model, BodyStyle bodyStyle, FuelType fuelType, User user) {
        this.title = title;
        this.year = year;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.enginePower = enginePower;
        this.doors = doors;
        this.colour = colour;
        this.description = description;
        this.price = price;
        this.model = model;
        this.bodyStyle = bodyStyle;
        this.fuelType = fuelType;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
