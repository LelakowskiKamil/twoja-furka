package com.lelakowsky.twojafurka.domain.offer;

import com.lelakowsky.twojafurka.domain.car.BodyStyle;
import com.lelakowsky.twojafurka.domain.car.CarModel;
import com.lelakowsky.twojafurka.domain.car.Color;
import com.lelakowsky.twojafurka.domain.car.FuelType;
import com.lelakowsky.twojafurka.domain.user.User;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
public class Offer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer year;
    private Integer mileage;
    private BigDecimal engineSize;
    private Integer enginePower;
    private Integer doors;
    private Color color;
    private String description;
    private Integer price;
    private BodyStyle bodyStyle;
    private FuelType fuelType;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private CarModel model;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private User user;
}
