package com.lelakowsky.twojafurka.service;

import com.lelakowsky.twojafurka.domain.car.BodyStyle;
import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import com.lelakowsky.twojafurka.domain.car.FuelType;
import com.lelakowsky.twojafurka.domain.offer.Offer;
import com.lelakowsky.twojafurka.domain.offer.OfferFilter;
import com.lelakowsky.twojafurka.dto.OfferDto;
import com.lelakowsky.twojafurka.repository.*;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface OffersService {

    List<Offer> filterByYearFrom(List<Offer> offers, Integer yearFrom);

    List<Offer> filterByYearTo(List<Offer> offers, Integer yearTo);

    List<Offer> filterByManufacturerId(List<Offer> offers, Integer getCarManufacturerId);

    List<Offer> filterByModelId(List<Offer> offers, Integer modelId, Integer getCarManufacturerId);

    List<Offer> filterOffers(List<Offer> offersToFilter, OfferFilter offerFilter);

    Page<Offer> paginateOffers(OfferFilter offerFilter);

    List<Offer> getOffers();

    List<Offer> getOffersOrdered(OfferFilter offerFilter);

    Offer createOffer(OfferDto offerDto);

    Optional<Offer> deleteOffer(Integer id);

    Offer saveOffer(Offer offer);

    Offer getOffer(Integer offerId);

}


