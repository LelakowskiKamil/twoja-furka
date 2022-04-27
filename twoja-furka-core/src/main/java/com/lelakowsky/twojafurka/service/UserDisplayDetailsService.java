package com.lelakowsky.twojafurka.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDisplayDetailsService extends UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s);
}
