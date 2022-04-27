package com.lelakowsky.twojafurka.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class OfferDto {

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
    @NotNull
    private Integer price;

    private CarModelDto carModel;

    private BodyStyleDto bodyStyle;

    private FuelTypeDto fuelType;

    private UserDto user;

    private String imageURL;

}
