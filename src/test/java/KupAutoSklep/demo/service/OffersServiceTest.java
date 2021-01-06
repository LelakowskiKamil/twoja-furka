package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.*;
import KupAutoSklep.demo.domain.repository.BodyStyleRepository;
import KupAutoSklep.demo.domain.repository.CarManufacturerRepository;
import KupAutoSklep.demo.domain.repository.FuelTypeRepository;
import KupAutoSklep.demo.domain.repository.OfferRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OffersServiceTest {
    CarManufacturer carManufacturer = new CarManufacturer(1, "carManufacturer");
    CarModel carModel = new CarModel(1, "carModel", carManufacturer, List.of());
    CarModel carModel2 = new CarModel(2, "carModel2", carManufacturer, List.of());
    BodyStyle bodyStyle = new BodyStyle(1, "bodyStyle", List.of());
    BodyStyle bodyStyle2 = new BodyStyle(2, "bodyStyle2", List.of());
    FuelType fuelType = new FuelType(1, "fuelType", List.of());
    FuelType fuelType2 = new FuelType(2, "fuelType2", List.of());
    Offer offer1 = new Offer(1, "Opel Astra", 1999, 100, BigDecimal.valueOf(5L), 55, 5, "red", "description", 3, carModel, bodyStyle, fuelType);
    Offer offer2 = new Offer(2, "BMW E46", 2000, 1000, BigDecimal.valueOf(6L), 66, 5, "red", "description", 2, carModel2, bodyStyle, fuelType2);
    Offer offer3 = new Offer(3, "Honda Civic", 2001, 1000, BigDecimal.valueOf(7L), 77, 5, "red", "description", 1, carModel, bodyStyle2, fuelType2);


    @Test
    void getOffersOrdered() {
        var mockEntityManager = mock(EntityManager.class);
        var mockOfferRepository = mock(OfferRepository.class);
        var listOfAllOffers = List.of(offer1, offer2, offer3);
        var listOfAllOffersSortByPriceOrderedASC = List.of(offer3, offer2, offer1);

        when(mockOfferRepository.findAll()).thenReturn(List.of(offer1, offer2, offer3));
        when(mockOfferRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))).thenReturn(List.of(offer3, offer2, offer1));
        var mockFuelTypeRepository = mock(FuelTypeRepository.class);
        var mockBodyStyleRepository = mock(BodyStyleRepository.class);
        var mockCarManufacturerRepository = mock(CarManufacturerRepository.class);
        var mockOfferFilter = mock(OfferFilter.class);
        when(mockOfferFilter.getOrder()).thenReturn("low to high");
        when(mockOfferFilter.getSortBy()).thenReturn("price");

        var testOfferService = new OffersService(mockEntityManager, mockOfferRepository, mockFuelTypeRepository, mockBodyStyleRepository, mockCarManufacturerRepository);

        var filterByManufacturerId_1 = testOfferService.filterByManufacturerId(listOfAllOffers, 1);
        assertThat(filterByManufacturerId_1).contains(offer1, offer2, offer3);

        var filterByManufacturerId_2 = testOfferService.filterByManufacturerId(listOfAllOffers, 2);
        assertThat(filterByManufacturerId_2).isEmpty();

        var filterByYear_produceBeforeOrIn2000 = testOfferService.filterByYearTo(listOfAllOffers, 2000);
        assertThat(filterByYear_produceBeforeOrIn2000).contains(offer1, offer2);
        assertThat(filterByYear_produceBeforeOrIn2000).doesNotContain(offer3);

        var filterByYear_produceAfterOrIn2001 = testOfferService.filterByYearFrom(listOfAllOffers, 2001);
        assertThat(filterByYear_produceAfterOrIn2001).contains(offer3);
        assertThat(filterByYear_produceAfterOrIn2001).doesNotContain(offer1, offer2);

        var result = testOfferService.getOffersOrdered(mockOfferFilter);
        assertThat(result).isEqualTo(listOfAllOffersSortByPriceOrderedASC);
    }

}