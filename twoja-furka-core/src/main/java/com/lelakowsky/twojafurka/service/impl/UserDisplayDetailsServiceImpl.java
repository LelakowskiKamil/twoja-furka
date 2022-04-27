package com.lelakowsky.twojafurka.service.impl;

import com.lelakowsky.twojafurka.domain.user.User;
import com.lelakowsky.twojafurka.domain.user.UserDisplayDetails;
import com.lelakowsky.twojafurka.repository.UserRepository;
import com.lelakowsky.twojafurka.service.UserDisplayDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDisplayDetailsServiceImpl implements UserDisplayDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s);
        if (user==null){
            throw new UsernameNotFoundException("Couldn't find user with that email");
        }
        return new UserDisplayDetails(user);
    }
}
