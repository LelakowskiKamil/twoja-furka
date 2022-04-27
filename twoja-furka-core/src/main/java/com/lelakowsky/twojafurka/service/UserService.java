package com.lelakowsky.twojafurka.service;


import com.lelakowsky.twojafurka.domain.user.User;
import com.lelakowsky.twojafurka.dto.UserDto;

public interface UserService  {
    User save(UserDto createUserCommand) ;

    void setEncodedPassword(UserDto userToCreate);

    User findByUsername(String username);

    User findById(Integer id);

}
