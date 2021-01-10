package KupAutoSklep.demo.web.controller;

import KupAutoSklep.demo.domain.model.*;
import KupAutoSklep.demo.service.CarModelService;
import KupAutoSklep.demo.service.OffersService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller(value = "/")
public class HomeController {

    private final OffersService offersService;
    private final CarModelService carModelService;

    public HomeController(OffersService offersService, CarModelService carModelService) {
        this.offersService = offersService;
        this.carModelService = carModelService;
    }


    @GetMapping("/")
    public String home() {

        return "layout";
    }


    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        return "offerDetails";
    }


//    @GetMapping("/offers")
//    public String home(Model model, OfferFilter offerFilter, Integer page) {
//        List<Offer> offers = offersService.getOffers(offerFilter.getPage());
//
//        if (offerFilter.getManufacturerId() != null) {
//            offers = offers.stream()
//                    .filter(s -> s.getModel().getManufacturer().getId().equals(offerFilter.getManufacturerId())).collect(Collectors.toList());
//            if (offerFilter.getModelId() != null) {
//                offers = offers.stream()
//                        .filter(s -> s.getModel().getId().equals(offerFilter.getModelId())).collect(Collectors.toList());
//            }
//            List<CarModel> carModels = offersService.getCarModelByManufacturer(offerFilter.getManufacturerId());
//            model.addAttribute("carModels", carModels);
//        }
//        if (offerFilter.getFromYear() != null) {
//            offers = offers.stream()
//                    .filter((s -> s.getYear() >= offerFilter.getFromYear())).collect(Collectors.toList());
//        }
//        if (offerFilter.getToYear() != null) {
//            offers = offers.stream()
//                    .filter((s -> s.getYear() <= offerFilter.getToYear())).collect(Collectors.toList());
//        }
//
//        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
//        model.addAttribute("offers", offers);
//        model.addAttribute("carManufacturers", carManufacturers);
//        return "offersList";
//    }

    @GetMapping("/offers")
    public String offersPage(
            Model model,
            OfferFilter offerFilter) {

        Page<Offer> offersPage = offersService.paginateOffers(offerFilter);


        model.addAttribute("offers", offersPage);
        int totalPages = offersPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        if (offerFilter.getManufacturerId() != null) {
            List<CarModel> carModels = carModelService.getCarModelByManufacturer(offerFilter.getManufacturerId());
            model.addAttribute("carModels", carModels);
        }

        List<String> attributes = Arrays.asList("title", "year", "mileage", "engineSize", "enginePower", "doors", "colour", "price", "model", "bodyStyle", "fuelType");
        List<String> orders = Arrays.asList("low to high", "high to low");
        List<Integer> pageSizeValues = Arrays.asList(1,2,4);
        model.addAttribute("attributes", attributes);
        model.addAttribute("orders", orders);
        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("pageSizeValues", pageSizeValues);
        return "offersList";
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Offer offer, CarManufacturer carManufacturer, OfferFilter offerFilter, Model model) {
        List<CarModel> carModels = carModelService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        return "createOffer";
    }

    @PostMapping("/newoffer")
    public String saveNewOffer(@Valid Offer offer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<CarModel> carModels = carModelService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyles();
            List<FuelType> fuelTypes = offersService.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);
            System.out.println("Blad!!!!");
            return "createOffer";
        }
        offer = offersService.createOffer(offer);
        System.out.println("dane poprawne");
        return "redirect:/offer/" + offer.getId();
    }

    @RequestMapping("/deleteoffer/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.deleteOffer(id);
        model.addAttribute("offer", offer);
        return "deleteOffer";
    }

    @RequestMapping("/editoffer/{id}")
    public String editOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        List<CarModel> carModels = carModelService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("offer", offer);
        model.addAttribute("header", "Edycja ogłoszenia");
        model.addAttribute("action", "/editoffer/" + id);
        return "editOffer";
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/editoffer/" + id);

            List<CarModel> carModels = carModelService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyles();
            List<FuelType> fuelTypes = offersService.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "editOffer";
        }
        offersService.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }
}
