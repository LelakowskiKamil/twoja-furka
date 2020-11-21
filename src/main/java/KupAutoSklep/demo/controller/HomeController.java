package KupAutoSklep.demo.controller;

import KupAutoSklep.demo.model.*;
import KupAutoSklep.demo.service.OffersService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    private OffersService offersService;

    public HomeController(OffersService offersService) {
        this.offersService = offersService;
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
    public String offersPage(Model model, OfferFilter offerFilter, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);





        Page<Offer> offersPage = offersService.findPaginated(PageRequest.of(currentPage - 1, pageSize),offerFilter);
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
            List<CarModel> carModels = offersService.getCarModelByManufacturer(offerFilter.getManufacturerId());
            model.addAttribute("carModels", carModels);
        }
        model.addAttribute("carManufacturers", carManufacturers);
        return "offersList";
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Offer offer, CarManufacturer carManufacturer, OfferFilter offerFilter, Model model) {
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("header", "Nowe ogłoszenie");
        model.addAttribute("action", "/newoffer");
        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        return "offerForm";
    }

    @PostMapping("/newoffer")
    public String saveNewOffer(@Valid Offer offer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<CarModel> carModels = offersService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyles();
            List<FuelType> fuelTypes = offersService.getFuelTypes();

            model.addAttribute("header", "Nowe ogłoszenie");
            model.addAttribute("action", "/newoffer");
            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);
            System.out.println("Blad!!!!");
            return "offerForm";
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
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("offer", offer);
        model.addAttribute("header", "Edycja ogłoszenia");
        model.addAttribute("action", "/editoffer/" + id);
        return "offerForm";
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/editoffer/" + id);

            List<CarModel> carModels = offersService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyles();
            List<FuelType> fuelTypes = offersService.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "offerForm";
        }
        offersService.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }
}
