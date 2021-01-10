package KupAutoSklep.demo.web.controller;

import KupAutoSklep.demo.exception.UserEmailAlreadyExistException;
import KupAutoSklep.demo.exception.UserNameAlreadyExistException;
import KupAutoSklep.demo.service.UserService;
import KupAutoSklep.demo.web.command.CreateUserCommand;
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
    public CreateUserCommand createUserCommand(){
        return new CreateUserCommand();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid CreateUserCommand createUserCommand,
                                      BindingResult bindings) {
        if (bindings.hasErrors()) {
            return "/registration";
        }
        try{
            userService.save(createUserCommand);
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
