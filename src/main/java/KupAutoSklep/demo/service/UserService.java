package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.model.login.Role;
import KupAutoSklep.demo.domain.model.login.User;
import KupAutoSklep.demo.web.command.CreateUserCommand;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserService  {
    User save(CreateUserCommand createUserCommand) ;

    void setEncodedPassword(CreateUserCommand userToCreate);

}
