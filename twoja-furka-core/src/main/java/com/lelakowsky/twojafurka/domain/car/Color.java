package com.lelakowsky.twojafurka.domain.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    RED(1, "Red"),
    WHITE(2, "White"),
    ORANGE(3,"Orange"),
    BLACK(4, "Black");


    private final Integer id;
    private final String name;
}
