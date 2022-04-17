package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.*;
import KupAutoSklep.demo.domain.repository.*;
import KupAutoSklep.demo.web.command.CreateOfferCommand;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OffersService {

    public OffersService(
            OfferRepository offerRepository,
            UserRepository userRepository, FuelTypeRepository fuelTypeRepository,
            BodyStyleRepository bodyStyleRepository,
            CarManufacturerRepository carManufacturerRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.bodyStyleRepository = bodyStyleRepository;
        this.carManufacturerRepository = carManufacturerRepository;
    }

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final BodyStyleRepository bodyStyleRepository;
    private final CarManufacturerRepository carManufacturerRepository;


    public Optional<CarManufacturer> gerCarManufacturer(int id) {
        return carManufacturerRepository.findById(id);
    }


    public List<CarManufacturer> getCarManufacturers() {
        return carManufacturerRepository.findAll();
    }

    public List<BodyStyle> getBodyStyles() {
        return bodyStyleRepository.findAll();
    }

    public List<FuelType> getFuelTypes() {
        return fuelTypeRepository.findAll();
    }


    public List<Offer> filterByYearFrom(List<Offer> offers, int yearFrom) {
        offers = offers.stream()
                .filter((s -> s.getYear() >= yearFrom)).collect(Collectors.toList());
        return offers;
    }

    public List<Offer> filterByYearTo(List<Offer> offers, int yearTo) {
        offers = offers.stream()
                .filter((s -> s.getYear() <= yearTo)).collect(Collectors.toList());
        return offers;
    }

    public List<Offer> filterByManufacturerId(List<Offer> offers, int manufacturerId) {
        offers = offers.stream()
                .filter(s -> s.getModel().getManufacturer().getId().equals(manufacturerId)).collect(Collectors.toList());

        return offers;
    }

    public List<Offer> filterByModelId(List<Offer> offers, int modelId, int manufacturerId) {
        offers = filterByManufacturerId(offers, manufacturerId);
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
        if (offerFilter.getManufacturerId() != null) {
            offersToFilter = filterByManufacturerId(offersToFilter, offerFilter.getManufacturerId());
            if (offerFilter.getModelId() != null) {
                offersToFilter = filterByModelId(offersToFilter, offerFilter.getModelId(), offerFilter.getManufacturerId());

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
        int pageSize;
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
        List<Offer> offers = offerRepository.findAll();

        return offers;
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


    public Offer getOffer(int offerId) {
        return offerRepository.findById(offerId);
    }


    public Offer createOffer(CreateOfferCommand createOfferCommand) {
        Offer offer = new Offer(
                createOfferCommand.getTitle(),
                createOfferCommand.getYear(),
                createOfferCommand.getMileage(),
                createOfferCommand.getEngineSize(),
                createOfferCommand.getEnginePower(),
                createOfferCommand.getDoors(),
                createOfferCommand.getColour(),
                createOfferCommand.getDescription(),
                createOfferCommand.getPrice(),
                createOfferCommand.getModel(),
                createOfferCommand.getBodyStyle(),
                createOfferCommand.getFuelType(),
                createOfferCommand.getUser(),
                createOfferCommand.getImageURL()
        );
        if (offer.getImageURL()== null || offer.getImageURL().trim().equals("")){
            offer.setImageURL("https://www.audiostereo.pl/uploads/logo/nophotoLarge.png");
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


