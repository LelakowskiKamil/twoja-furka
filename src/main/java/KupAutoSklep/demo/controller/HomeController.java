package KupAutoSklep.demo.controller;

import KupAutoSklep.demo.model.*;
import KupAutoSklep.demo.service.OffersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private OffersService offersService;

    public HomeController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/models")
    public String models(Model model) {
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        model.addAttribute("carManufacturers", carManufacturers);
        return "offersList";
    }

    @GetMapping("/fueltypes")
    public String fueltypes(Model model) {
        List<FuelType> fuelTypes = offersService.getFuelTypes();
        model.addAttribute("fuelTypes", fuelTypes);
        return "offersList";
    }

    @GetMapping("/bodystyles")
    public String bodystyles(Model model) {
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        model.addAttribute("bodyStyles", bodyStyles);
        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        return "offerDetails";
    }

    @GetMapping("/offers")
    public String home(Model model, OfferFilter offerFilter) {
        List<Offer> offers= offersService.getOffers();

        if (offerFilter.getManufacturerId() != null) {
            offers = offers.stream()
                    .filter(s -> s.getModel().getManufacturer().getId().equals(offerFilter.getManufacturerId())).collect(Collectors.toList());
            if (offerFilter.getModelId() != null) {
                offers = offers.stream()
                        .filter(s -> s.getModel().getId().equals(offerFilter.getModelId())).collect(Collectors.toList());
            }
            List<CarModel> carModels = offersService.getCarModelByManufacturer(offerFilter.getManufacturerId());
            model.addAttribute("carModels", carModels);
        }
        if (offerFilter.getFromYear()!=null){
            offers = offers.stream()
                    .filter((s -> s.getYear() >= offerFilter.getFromYear())).collect(Collectors.toList());
        }
        if (offerFilter.getToYear()!=null){
            offers = offers.stream()
                    .filter((s -> s.getYear() <= offerFilter.getToYear())).collect(Collectors.toList());
        }
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        model.addAttribute("offers", offers);
        model.addAttribute("carManufacturers", carManufacturers);
        return "offersList";
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Offer offer, CarManufacturer carManufacturer, OfferFilter offerFilter, Model model){
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        return "offerForm";
    }

    @PostMapping("/newoffer")
    public String saveNewOffer(@Valid Offer offer, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            List<CarModel> carModels = offersService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyles();
            List<FuelType> fuelTypes = offersService.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);
            System.out.println("Blad!!!!");
            return "offerForm";
        }
        offer = offersService.createOffer(offer);
        System.out.println("dane poprawne");
        return "redirect:/offer/"+offer.getId();
    }

    @RequestMapping("/deleteoffer/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id) {
Offer offer = offersService.deleteOffer(id);
model.addAttribute("offer", offer);
        return "deleteOffer";
    }
}
