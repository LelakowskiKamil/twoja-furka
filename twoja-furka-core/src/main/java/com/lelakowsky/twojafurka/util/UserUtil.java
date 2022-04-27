package com.lelakowsky.twojafurka.util;

import com.lelakowsky.twojafurka.domain.user.User;
import com.lelakowsky.twojafurka.domain.user.UserSummary;
import com.lelakowsky.twojafurka.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public static UserSummary toUserSummary(UserDto userDto) {
        UserSummary userSummary = new UserSummary();
        userSummary.setPassword(userDto.getPassword());
        userSummary.setEnabled(userDto.isEnabled());
        userSummary.setUsername(userDto.getUsername());
        userSummary.setEmail(userDto.getEmail());
        return userSummary;
    }

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstname(user.getFirstname());
        userDto.setUsername(user.getUsername());
        userDto.setLastname(user.getLastname());
        userDto.setPhone(user.getPhone());
        userDto.setEnabled(user.isEnabled());
        return userDto;
    }
}
