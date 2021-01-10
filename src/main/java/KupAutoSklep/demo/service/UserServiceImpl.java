package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.repository.UserRepository;
import KupAutoSklep.demo.converter.UserConverter;
import KupAutoSklep.demo.domain.model.login.Role;
import KupAutoSklep.demo.domain.model.login.User;
import KupAutoSklep.demo.exception.UserEmailAlreadyExistException;
import KupAutoSklep.demo.exception.UserNameAlreadyExistException;
import KupAutoSklep.demo.web.command.CreateUserCommand;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
    }

    @Override
    public User save(CreateUserCommand createUserCommand) {
    //    setEncodedPassword(createUserCommand);
    //    UserSummary userSummary = userConverter.toUserSummary(createUserCommand);
        User user = new User(createUserCommand.getEmail(), createUserCommand.getUsername(), passwordEncoder.encode(createUserCommand.getPassword()), Arrays.asList(new Role("ROLE_USER")), createUserCommand.isEnabled());
        if (userRepository.existsByEmail(
                user.getEmail())) {
            throw new UserEmailAlreadyExistException(String.format(
                    "User with %s userEmail already exist in DB",
                    user.getEmail()));

        }else if (userRepository.existsByUsername(
                user.getUsername())) {
            throw new UserNameAlreadyExistException(String.format(
                    "User with %s username already exist in DB",
                    user.getUsername()));
        }
        return userRepository.save(user);
    }


    public void setEncodedPassword(CreateUserCommand userToCreate) {
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));

    }

    private Collection<? extends GrantedAuthority>
    mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }



}
