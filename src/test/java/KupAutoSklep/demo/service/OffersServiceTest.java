package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.*;
import KupAutoSklep.demo.domain.model.login.Role;
import KupAutoSklep.demo.domain.model.login.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("tests")
class OffersServiceTest {
    Offer createDefaultOffer() {
        Offer offer1 = new Offer();
        offer1.setTitle("Opel Astra");
        offer1.setId(1);
        offer1.setYear(1999);
        offer1.setDoors(5);
        offer1.setEnginePower(5);
        offer1.setPrice(5);
        offer1.setMileage(100);
        offer1.setColour("red");
        offer1.setEngineSize(BigDecimal.valueOf(5L));
        offer1.setDescription("description");
        offer1.setModel(createDefaultCarModel());
        offer1.setUser(createDefaultUser());
        offer1.setFuelType(createDefaultFuelType());
        offer1.setBodyStyle(createDefaultBodyStyle());

        return offer1;
    }

    Offer createDefaultOffer(int year) {
        Offer offer1 = new Offer();
        offer1.setTitle("Opel Astra");
        offer1.setId(1);
        offer1.setYear(year);
        offer1.setDoors(5);
        offer1.setEnginePower(5);
        offer1.setPrice(5);
        offer1.setMileage(100);
        offer1.setColour("red");
        offer1.setEngineSize(BigDecimal.valueOf(5L));
        offer1.setDescription("description");
        offer1.setModel(createDefaultCarModel());
        offer1.setUser(createDefaultUser());
        offer1.setFuelType(createDefaultFuelType());
        offer1.setBodyStyle(createDefaultBodyStyle());

        return offer1;
    }

    CarManufacturer createDefaultCarManufacturer() {
        CarManufacturer carManufacturer = new CarManufacturer();
        carManufacturer.setId(1);
        carManufacturer.setName("carManufacturer");
        return carManufacturer;
    }

    BodyStyle createDefaultBodyStyle() {
        BodyStyle bodyStyle = new BodyStyle();
        bodyStyle.setId(1);
        bodyStyle.setName("bodyStyle");
        return bodyStyle;
    }

    FuelType createDefaultFuelType() {
        FuelType fuelType = new FuelType();
        fuelType.setId(1);
        fuelType.setName("fuelType");
        return fuelType;
    }

    Role createAdminRole() {
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");
        return role;
    }

    CarModel createDefaultCarModel() {
        CarModel carModel = new CarModel();
        carModel.setId(1);
        carModel.setName("carModel");
        carModel.setManufacturer(createDefaultCarManufacturer());
        return carModel;
    }

    User createDefaultUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("!Haslo123");
        user.setFirstname("username");
        user.setEmail("username@email");
        user.setRoles(Collections.singletonList(createAdminRole()));
        return user;
    }

    @Autowired
    private OffersService offersService;

    @Test
    void filterByYearTest() {
        Offer offer1 = createDefaultOffer(2001);
        Offer offer2 = createDefaultOffer(1996);
        Offer offer3 = createDefaultOffer(2005);
        List<Offer> offers = List.of(offer1, offer2, offer3);
        int yearFrom = 1999;
        List<Offer> filteredOffers = offersService.filterByYearFrom(offers, yearFrom);
        assertThat(List.of(offer1, offer3).containsAll(filteredOffers)).isTrue();
        assertThat(filteredOffers.contains(offer2)).isFalse();
    }

    @Test
    void filterToYearTest() {
        Offer offer1 = createDefaultOffer(2001);
        Offer offer2 = createDefaultOffer(1996);
        Offer offer3 = createDefaultOffer(2005);
        List<Offer> offers = List.of(offer1, offer2, offer3);
        int yearFrom = 2001;
        List<Offer> filteredOffers = offersService.filterByYearTo(offers, yearFrom);
        assertThat(List.of(offer1, offer3).containsAll(filteredOffers)).isFalse();
        assertThat(filteredOffers.contains(offer2)).isTrue();
    }


}