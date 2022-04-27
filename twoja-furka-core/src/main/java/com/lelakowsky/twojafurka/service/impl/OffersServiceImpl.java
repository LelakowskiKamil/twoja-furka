package com.lelakowsky.twojafurka.service.impl;

import com.lelakowsky.twojafurka.domain.car.BodyStyle;
import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import com.lelakowsky.twojafurka.domain.car.FuelType;
import com.lelakowsky.twojafurka.domain.offer.Offer;
import com.lelakowsky.twojafurka.domain.offer.OfferFilter;
import com.lelakowsky.twojafurka.dto.OfferDto;
import com.lelakowsky.twojafurka.repository.*;
import com.lelakowsky.twojafurka.service.OffersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OffersServiceImpl implements OffersService {

    private final OfferRepository offerRepository;

    public List<Offer> filterByYearFrom(List<Offer> offers, Integer yearFrom) {
        offers = offers.stream()
                .filter((s -> s.getYear() >= yearFrom)).collect(Collectors.toList());
        return offers;
    }

    public List<Offer> filterByYearTo(List<Offer> offers, Integer yearTo) {
        offers = offers.stream()
                .filter((s -> s.getYear() <= yearTo)).collect(Collectors.toList());
        return offers;
    }

    public List<Offer> filterByManufacturerId(List<Offer> offers, Integer getCarManufacturerId) {
        offers = offers.stream()
                .filter(offer -> offer.getModel().getManufacturer().getId().equals(getCarManufacturerId)).collect(Collectors.toList());

        return offers;
    }

    public List<Offer> filterByModelId(List<Offer> offers, Integer modelId, Integer getCarManufacturerId) {
        offers = filterByManufacturerId(offers, getCarManufacturerId);
        offers = offers.stream()
                .filter(s -> s.getModel().getId().equals(modelId)).collect(Collectors.toList());
        return offers;
    }

    public List<Offer> filterOffers(List<Offer> offersToFilter, OfferFilter offerFilter) {
        if (offerFilter.getFromYear() != null) {
            offersToFilter = filterByYearFrom(offersToFilter, offerFilter.getFromYear());
        }
        if (offerFilter.getToYear() != null) {
            offersToFilter = filterByYearTo(offersToFilter, offerFilter.getToYear());
        }
        if (offerFilter.getCarManufacturerId() != null) {
            offersToFilter = filterByManufacturerId(offersToFilter, offerFilter.getCarManufacturerId());
            if (offerFilter.getModelId() != null) {
                offersToFilter = filterByModelId(offersToFilter, offerFilter.getModelId(), offerFilter.getCarManufacturerId());

            }
        }
        return offersToFilter;

    }

    public Page<Offer> paginateOffers(OfferFilter offerFilter) {
        int page;
        if (offerFilter.getPage() == null) {
            page = 0;
        } else {
            page = offerFilter.getPage() - 1;
        }

        Pageable pageable = PageRequest.of(page, 4);
        Integer pageSize;
        if (offerFilter.getPageSize() == null) {
            pageSize = pageable.getPageSize();
        } else {
            pageSize = offerFilter.getPageSize();
            offerFilter.setPageSize(pageSize);

        }
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Offer> list;
        List<Offer> offers;

        offers = getOffersOrdered(offerFilter);

        offers = filterOffers(offers, offerFilter);


        if (offers.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, offers.size());
            list = offers.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), offers.size());
    }

    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    public List<Offer> getOffersOrdered(OfferFilter offerFilter) {
        String sort;
        String order;
        if (offerFilter.getSortBy() == null || offerFilter.getSortBy().equals("")) {
            sort = "price";
        } else {
            sort = offerFilter.getSortBy();
            offerFilter.setSortBy(sort);

        }

        if (offerFilter.getOrder() == null || offerFilter.getOrder().equals("")) {
            order = "ASC";
        } else {
            if (offerFilter.getOrder().equals("low to high")) {
                order = "ASC";
                offerFilter.setOrder("low to high");
            } else {
                order = "DESC";
                offerFilter.setOrder("high to low");
            }
        }

        return offerRepository.findAll(Sort.by(Sort.Direction.fromString(order), sort));
    }


    public Offer getOffer(Integer offerId) {
        return offerRepository.getById(offerId);
    }


    public Offer createOffer(OfferDto offerDto) {
        Offer offer = new Offer();
//TODO z konwertera
        if (offer.getImageUrl()== null || offer.getImageUrl().trim().equals("")){
            offer.setImageUrl("https://www.audiostereo.pl/uploads/logo/nophotoLarge.png");
        }
        saveOffer(offer);
        return offer;
    }

    public Optional<Offer> deleteOffer(Integer id) {
        Optional<Offer> offer = offerRepository.findById(id);
        offerRepository.delete(offer.orElseThrow());
        return offer;
    }

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }


}


