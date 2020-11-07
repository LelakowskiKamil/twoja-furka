package KupAutoSklep.demo.controller;

import KupAutoSklep.demo.model.*;
import KupAutoSklep.demo.service.OffersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

private OffersService offersService;

    public HomeController(OffersService offersService) {
        this.offersService = offersService;
    }

@GetMapping("/models")
    public String models(Model model){
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        model.addAttribute("carManufacturers",carManufacturers);
       return "offersList";
    }

    @GetMapping("/fueltypes")
    public String fueltypes(Model model){
        List<FuelType> fuelTypes = offersService.getFuelTypes();
        model.addAttribute("fuelTypes",fuelTypes);
        return "offersList";
    }

    @GetMapping("/bodystyles")
    public String bodystyles(Model model){
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        model.addAttribute("bodyStyles",bodyStyles);
        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        return "offerDetails";
    }

    @GetMapping("/offers")
    public String home(Model model){
        List<Offer> offers = offersService.getOffers();
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        List<CarModel> carModels = offersService.getCarModels();
        model.addAttribute("offers",offers);
        model.addAttribute("carManufacturers",carManufacturers);
        model.addAttribute("carModels",carModels);
        return "offersList";
    }
}
