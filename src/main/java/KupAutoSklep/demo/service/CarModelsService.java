package KupAutoSklep.demo.service;

import KupAutoSklep.demo.model.CarModel;
import KupAutoSklep.demo.model.Offer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CarModelsService {

    @PersistenceContext
    private EntityManager em;

    public List<CarModel> getModelsByManufacturer(int manufacturerId){
        String jpql = "select m from CarModel m where m.manufacturer.id = :id order by m.id";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        query.setParameter("id",manufacturerId);
        List<CarModel> result = query.getResultList();
        return result;
    }


}
