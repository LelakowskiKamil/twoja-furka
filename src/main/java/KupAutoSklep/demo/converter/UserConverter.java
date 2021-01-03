package KupAutoSklep.demo.converter;

import KupAutoSklep.demo.data.UserSummary;
import KupAutoSklep.demo.web.command.CreateUserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserSummary toUserSummary(CreateUserCommand createUserCommand) {
        return new UserSummary(createUserCommand.getEmail(),createUserCommand.getUsername(),createUserCommand.getPassword());
    }
}
