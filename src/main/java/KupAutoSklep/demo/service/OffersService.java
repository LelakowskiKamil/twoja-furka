package KupAutoSklep.demo.service;

import KupAutoSklep.demo.*;
import KupAutoSklep.demo.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OffersService {

    @PersistenceContext
    private EntityManager em;

    public OffersService(EntityManager em,
                         OfferRepository offerRepository,
                         CarModelRepository carModelRepository,
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


    public List<Offer> filterByYearFrom(List<Offer> offers, int yearFrom){
        offers = offers.stream()
                .filter((s -> s.getYear() >= yearFrom)).collect(Collectors.toList());
        return offers;
    }
    public List<Offer> filterByYearTo(List<Offer> offers, int yearTo){
        offers = offers.stream()
                .filter((s -> s.getYear() <= yearTo)).collect(Collectors.toList());
        return offers;
    }

    public List<Offer> filterByManufacturerId(List<Offer> offers, int manufacturerId){
            offers = offers.stream()
                    .filter(s -> s.getModel().getManufacturer().getId().equals(manufacturerId)).collect(Collectors.toList());

        return offers;
    }
    public List<Offer> filterByModelId(List<Offer> offers, int modelId, int manufacturerId){
            offers = filterByManufacturerId(offers,manufacturerId);
        offers = offers.stream()
                .filter(s -> s.getModel().getId().equals(modelId)).collect(Collectors.toList());
        return offers;
    }


    public Page<Offer> findPaginated(Pageable pageable, OfferFilter offerFilter ) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Offer> list;

        List<Offer> offers = getOffers();
        if (offerFilter.getFromYear() != null) {
            offers = filterByYearFrom(offers,offerFilter.getFromYear());
        }
        if (offerFilter.getToYear() != null) {
            offers = filterByYearTo(offers,offerFilter.getToYear());
        }
        if (offerFilter.getManufacturerId() != null) {
            offers = filterByManufacturerId(offers,offerFilter.getManufacturerId());
            if (offerFilter.getModelId() != null) {
                offers = filterByModelId(offers, offerFilter.getModelId(), offerFilter.getManufacturerId());

            }
        }

        System.out.println(offers.toString());

        if (offers.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, offers.size());
            list = offers.subList(startItem, toIndex);
        }

        Page<Offer> offerPage
                = new PageImpl<Offer>(list, PageRequest.of(currentPage, pageSize), offers.size());
        System.out.println(offerPage.toString());
        System.out.println(offerPage.getTotalPages());
        System.out.println(offerPage.getTotalElements());
        return offerPage;
    }

    public List<Offer> getOffers(){
        List<Offer> offers = offerRepository.findAll();

        return offers;
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


