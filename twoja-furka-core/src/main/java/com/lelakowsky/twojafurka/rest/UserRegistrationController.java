package com.lelakowsky.twojafurka.rest;

import com.lelakowsky.twojafurka.dto.UserDto;
import com.lelakowsky.twojafurka.exception.UserEmailAlreadyExistException;
import com.lelakowsky.twojafurka.exception.UserNameAlreadyExistException;
import com.lelakowsky.twojafurka.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {

        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto createUserCommand(){
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult bindings) {
        if (bindings.hasErrors()) {
            return "/registration";
        }
        try{
            userService.save(userDto);
            return "redirect:/registration?success";
        }catch (UserEmailAlreadyExistException uae){
            bindings.rejectValue("email",null, "User with this e-mail already exist");
            return "/registration";
        }catch (UserNameAlreadyExistException uae){
            bindings.rejectValue("username",null, "User with this username already exist");
            return "/registration";
        }catch (RuntimeException re){
            bindings.rejectValue(null,null, "Error");
            return "/registration";
        }

    }

}
