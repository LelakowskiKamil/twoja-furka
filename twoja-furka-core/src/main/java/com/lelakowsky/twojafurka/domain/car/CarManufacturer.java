package com.lelakowsky.twojafurka.domain.car;

import com.lelakowsky.twojafurka.domain.offer.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class CarManufacturer {

@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<CarModel> carModels;
}
