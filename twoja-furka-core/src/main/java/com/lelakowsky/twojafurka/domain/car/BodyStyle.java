package com.lelakowsky.twojafurka.domain.car;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BodyStyle {
    HATCHBACK(1, "Hatchback"),
    SEDAN(2, "Sedan"),
    COUPE(3, "Coupe"),
    KOMBI(4, "Kombi"),
    SUV(5, "Suv"),
    PICKUP(6, "Pickup"),
    CABRIO(7, "Cabrio");

    private final Integer id;
    private final String name;

}
