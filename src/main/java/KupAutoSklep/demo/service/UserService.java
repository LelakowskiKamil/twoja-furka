package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.login.User;
import KupAutoSklep.demo.web.command.CreateUserCommand;

public interface UserService  {
    User save(CreateUserCommand createUserCommand) ;
}
