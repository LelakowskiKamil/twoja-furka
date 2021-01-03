package KupAutoSklep.demo.service;

import KupAutoSklep.demo.web.command.CreateUserCommand;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface CustomUserDetailService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

}
