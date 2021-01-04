package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.CarModel;
import KupAutoSklep.demo.domain.repository.CarModelRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CarModelService {

    @PersistenceContext
    private final EntityManager em;
    private final CarModelRepository carModelRepository;

    public CarModelService(EntityManager em, CarModelRepository carModelRepository) {
        this.em = em;
        this.carModelRepository = carModelRepository;
    }

    public List<CarModel> getCarModelByManufacturer(int manufacturerId) {

        return carModelRepository.findCarModelsByManufacturer_Id(manufacturerId);
    }

    public CarModel getCarModel(int id) {
        return em.find(CarModel.class, id);
    }

    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }


}
