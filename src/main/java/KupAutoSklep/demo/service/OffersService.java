package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.*;
import KupAutoSklep.demo.domain.repository.BodyStyleRepository;
import KupAutoSklep.demo.domain.repository.CarManufacturerRepository;
import KupAutoSklep.demo.domain.repository.FuelTypeRepository;
import KupAutoSklep.demo.domain.repository.OfferRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OffersService {

    @PersistenceContext
    private EntityManager em;

    public OffersService(EntityManager em,
                         OfferRepository offerRepository,
                         FuelTypeRepository fuelTypeRepository,
                         BodyStyleRepository bodyStyleRepository,
                         CarManufacturerRepository carManufacturerRepository) {
        this.em = em;
        this.offerRepository = offerRepository;
        this.carModelRepository = carModelRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.bodyStyleRepository = bodyStyleRepository;
        this.carManufacturerRepository = carManufacturerRepository;
    }

    private final OfferRepository offerRepository;
    private final CarModelRepository carModelRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final BodyStyleRepository bodyStyleRepository;
    private final CarManufacturerRepository carManufacturerRepository;


    public CarManufacturer gerCarManufacturer(int id) {
        return em.find(CarManufacturer.class, id);
    }

    public CarModel getCarModel(int id) {
        return em.find(CarModel.class, id);
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

    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
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


    public Page<Offer> paginateOffers(Pageable pageable, OfferFilter offerFilter) {
        int pageSize = pageable.getPageSize();
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
        Page<Offer> offerPage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), offers.size());
        System.out.println(offerPage.toString());
        System.out.println(offerPage.getTotalPages());
        System.out.println(offerPage.getTotalElements());
        return offerPage;
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
        }else{
            sort = offerFilter.getSortBy();
            offerFilter.setSortBy(sort);
        }

        if (offerFilter.getOrder() == null || offerFilter.getOrder().equals("")) {
            order = "ASC";
        }else{
            if (offerFilter.getOrder().equals("low to high")){
                order = "ASC";
                offerFilter.setOrder("low to high");
            }else {
                order = "DESC";
                offerFilter.setOrder("high to low");
            }
        }

        return offerRepository.findAll(Sort.by(Sort.Direction.fromString(order),sort));
    }


    public Offer getOffer(int offerId) {
        return offerRepository.findById(offerId);
    }


    public List<CarModel> getCarModelByManufacturer(int manufacturerId) {

        return carModelRepository.findCarModelsByManufacturer_Id(manufacturerId);
    }

    public Offer createOffer(Offer offer) {
        em.persist(offer);
        return offer;
    }

    public Offer deleteOffer(Integer id) {
        Offer offer = em.find(Offer.class, id);
        em.remove(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer) {
        return em.merge(offer);
    }


}


