package com.lelakowsky.twojafurka.rest;

import com.lelakowsky.twojafurka.domain.car.BodyStyle;
import com.lelakowsky.twojafurka.domain.car.CarManufacturer;
import com.lelakowsky.twojafurka.domain.car.CarModel;
import com.lelakowsky.twojafurka.domain.car.FuelType;
import com.lelakowsky.twojafurka.domain.offer.Offer;
import com.lelakowsky.twojafurka.domain.offer.OfferFilter;
import com.lelakowsky.twojafurka.domain.user.User;
import com.lelakowsky.twojafurka.dto.OfferDto;
import com.lelakowsky.twojafurka.dto.UserDto;
import com.lelakowsky.twojafurka.service.CarManufacturerService;
import com.lelakowsky.twojafurka.service.CarModelService;
import com.lelakowsky.twojafurka.service.OffersService;
import com.lelakowsky.twojafurka.service.UserService;
import com.lelakowsky.twojafurka.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(path = "/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OffersService offersService;
    private final UserService userService;
    private final CarModelService carModelService;
    private final CarManufacturerService carManufacturerService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        return "offerDetails";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String offersPage(
            Model model,
            OfferFilter offerFilter) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Page<Offer> offersPage = offersService.paginateOffers(offerFilter);

        model.addAttribute("offers", offersPage);
        int totalPages = offersPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        List<CarManufacturer> carManufacturers = carManufacturerService.getCarManufacturers();
        if (offerFilter.getCarManufacturerId() != null) {
            List<CarModel> carModels = carModelService.getCarModelByManufacturerId(offerFilter.getCarManufacturerId());
            model.addAttribute("carModels", carModels);
        }
        List<String> attributes = Arrays.asList("title", "year", "mileage", "engineSize", "enginePower", "doors", "colour", "price", "model", "bodyStyle", "fuelType");
        List<String> orders = Arrays.asList("low to high", "high to low");
        List<Integer> pageSizeValues = Arrays.asList(1, 2, 4, 8, 16);
        model.addAttribute("attributes", attributes);
        model.addAttribute("orders", orders);
        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("pageSizeValues", pageSizeValues);

        return "offersList";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String newOfferForm(Offer offer, OfferFilter offerFilter, Model model) {
        List<CarModel> carModels = carModelService.getCarModels();
        List<BodyStyle> bodyStyles = Arrays.stream(BodyStyle.values()).collect(Collectors.toList());
        List<FuelType> fuelTypes = Arrays.stream(FuelType.values()).collect(Collectors.toList());

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        return "createOffer";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/new")
    public String saveNewOffer(@ModelAttribute("offer") @Valid OfferDto offerDto, BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        UserDto userDto = UserUtil.toUserDto(user);
        offerDto.setUser(userDto);
        if (bindingResult.hasErrors()) {

            List<CarModel> carModels = carModelService.getCarModels();
            List<BodyStyle> bodyStyles = Arrays.stream(BodyStyle.values()).collect(Collectors.toList());
            List<FuelType> fuelTypes = Arrays.stream(FuelType.values()).collect(Collectors.toList());

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);
            return "createOffer";
        }

        Offer createdOffer = offersService.createOffer(offerDto);
        return "redirect:/offers/" + createdOffer.getId();
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id) {
        Optional<Offer> offer = offersService.deleteOffer(id);
        model.addAttribute("offer", offer.orElseThrow());
        return "deleteOffer";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    public String editOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        List<CarModel> carModels = carModelService.getCarModels();
        List<BodyStyle> bodyStyles = Arrays.stream(BodyStyle.values()).collect(Collectors.toList());
        List<FuelType> fuelTypes = Arrays.stream(FuelType.values()).collect(Collectors.toList());

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("offer", offer);
        model.addAttribute("header", "Edycja ogłoszenia");
        model.addAttribute("action", "/edit/" + id);
        return "editOffer";
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/edit/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/edit/" + id);

            List<CarModel> carModels = carModelService.getCarModels();
            List<BodyStyle> bodyStyles = Arrays.stream(BodyStyle.values()).collect(Collectors.toList());
            List<FuelType> fuelTypes = Arrays.stream(FuelType.values()).collect(Collectors.toList());

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "editOffer";
        }
        offersService.saveOffer(offer);

        return "redirect:/offers/" + offer.getId();
    }


}
