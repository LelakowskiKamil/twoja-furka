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
import com.lelakowsky.twojafurka.service.CarModelService;
import com.lelakowsky.twojafurka.service.OffersService;
import com.lelakowsky.twojafurka.service.UserService;
import com.lelakowsky.twojafurka.service.impl.CarModelServiceImpl;
import com.lelakowsky.twojafurka.service.impl.OffersServiceImpl;
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
@RequiredArgsConstructor
public class HomeController {

    private final OffersService offersService;
    private final UserService userService;
    private final CarModelService carModelService;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String home() {
        return "layout";
    }


    @GetMapping("/userinfo/{id}")
    public String userDetail(Model model, @PathVariable("id") Integer id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/userinfo/")
    public String userDetailUsername(Model model, @RequestParam(value="username") String username) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "userInfo";
    }





    @RequestMapping("/editoffer/{id}")
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
        model.addAttribute("action", "/editoffer/" + id);
        return "editOffer";
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/editoffer/" + id);

            List<CarModel> carModels = carModelService.getCarModels();
            List<BodyStyle> bodyStyles = Arrays.stream(BodyStyle.values()).collect(Collectors.toList());
            List<FuelType> fuelTypes = Arrays.stream(FuelType.values()).collect(Collectors.toList());

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "editOffer";
        }
        offersService.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }
}
