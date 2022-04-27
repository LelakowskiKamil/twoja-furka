package com.lelakowsky.twojafurka.domain.offer;

import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferFilter {
    private Integer carManufacturerId;
    private Integer modelId;
    private Integer fromYear;
    private Integer toYear;
    private Integer page;
    private String sortBy;
    private String order;
    private Integer pageSize;
}
