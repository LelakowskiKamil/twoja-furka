package com.lelakowsky.twojafurka.domain.car;

import com.lelakowsky.twojafurka.domain.offer.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum FuelType {

    GASOLINE(1, "Gasoline"),
    DIESEL(2, "Diesel"),
    LPG(3, "LPG"),
    CNG(4, "CNG"),
    HYBRID(5, "Hybrid"),
    ELECTRIC(6, "Electric"),
    GASOLINE_LPG(7, "Gasoline + LPG"),
    GASOLINE_CNG(8, "Gasoline + CNG");

    private final Integer id;
    private final String name;

}
