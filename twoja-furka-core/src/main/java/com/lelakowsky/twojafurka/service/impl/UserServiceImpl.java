package com.lelakowsky.twojafurka.service.impl;

import com.lelakowsky.twojafurka.domain.user.Role;
import com.lelakowsky.twojafurka.domain.user.User;
import com.lelakowsky.twojafurka.dto.UserDto;
import com.lelakowsky.twojafurka.exception.UserEmailAlreadyExistException;
import com.lelakowsky.twojafurka.exception.UserNameAlreadyExistException;
import com.lelakowsky.twojafurka.repository.UserRepository;
import com.lelakowsky.twojafurka.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(UserDto createUserCommand) {
        User user = new User();
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");

        user.setRoles( Arrays.asList(role));
        user.setEmail(createUserCommand.getEmail());
        user.setUsername(createUserCommand.getUsername());
        user.setPassword(passwordEncoder.encode(createUserCommand.getPassword()));
        user.setEnabled(createUserCommand.isEnabled());
        user.setOffers(Arrays.asList());
        user.setFirstname(createUserCommand.getFirstname() != null ? createUserCommand.getFirstname() : null);
        user.setLastname(createUserCommand.getLastname() != null ? createUserCommand.getLastname() : null);
        user.setPhone(createUserCommand.getPhone() != null ? createUserCommand.getPhone() : null);
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


    public void setEncodedPassword(UserDto userToCreate) {
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.getById(id);
    }

    private Collection<? extends GrantedAuthority>
    mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }



}
