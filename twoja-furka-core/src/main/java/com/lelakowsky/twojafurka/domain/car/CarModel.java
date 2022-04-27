package com.lelakowsky.twojafurka.domain.car;

import com.lelakowsky.twojafurka.domain.offer.Offer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private CarManufacturer manufacturer;

    @OneToMany(mappedBy = "model")
    private List<Offer> offers;


}
