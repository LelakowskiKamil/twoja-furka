package KupAutoSklep.demo.service;

import KupAutoSklep.demo.model.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OffersService {

    @PersistenceContext
    private EntityManager em;

    public CarManufacturer gerCarManufacturer(int id) {
        return em.find(CarManufacturer.class, id);
    }

    public CarModel getCarModel(int id) {
        return em.find(CarModel.class, id);
    }

    public List<CarManufacturer> getCarManufacturers() {
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();
        return result;
    }

    public List<BodyStyle> getBodyStyles() {
        String jpql = "select bs from BodyStyle bs order by bs.name";
        TypedQuery<BodyStyle> query = em.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;
    }

    public List<FuelType> getFuelTypes() {
        String jpql = "select ft from FuelType ft order by ft.name";
        TypedQuery<FuelType> query = em.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModels() {
        String jpql = "select cm from CarModel cm  order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        List<CarModel> result = query.getResultList();
        return result;
    }

    public List<Offer> getOffers() {
        String jpql = "select o from Offer o order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        List<Offer> result = query.getResultList();
        return result;
    }

    public Offer getOffer(int offerId) {
        String jpql = "select o from Offer o where o.id = :id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", offerId);
        Offer result = query.getSingleResult();
        return result;
    }

    public CarManufacturer getCarManufacturer(int carManufacturerId) {
        String jpql = "select cm from CarManufacturer cm where cm.id = :id";
        TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
        query.setParameter("id", carManufacturerId);
        CarManufacturer result = query.getSingleResult();
        return result;
    }

    public List<Offer> getOffersByModel(int modelId) {
        String jpql = "select o from Offer o where o.model.id = :id order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", modelId);
        List<Offer> result = query.getResultList();
        return result;
    }

    public List<Offer> getOffersByManufacturer(int manufacturerId) {
        String jpql = "select o from Offer o where o.model.manufacturer.id = :id order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);
        List<Offer> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModelByManufacturer(int manufacturerId) {
        String jpql = "select cm from CarModel cm WHERE cm.manufacturer.id = :id order by cm.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        query.setParameter("id", manufacturerId);
        List<CarModel> result = query.getResultList();
        return result;
    }

    public List<Offer> getOffersByFromYear(int year) {
        String jpql = "select o from Offer o where o.year >= :year order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("year", year);
        List<Offer> result = query.getResultList();
        return result;
    }

    public List<Offer> getOffersByToYear(int year) {
        String jpql = "select o from Offer o where o.year >= :year order by o.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("year", year);
        List<Offer> result = query.getResultList();
        return result;
    }

    public Offer createOffer(Offer offer) {
        em.persist(offer);
        return offer;
    }

    public Offer deleteOffer(Integer id) {
        Offer offer = em.find(Offer.class,id);
        em.remove(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer){
        return em.merge(offer);
    }


}


