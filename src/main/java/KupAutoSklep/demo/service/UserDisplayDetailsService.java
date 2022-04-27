package KupAutoSklep.demo.service;

import KupAutoSklep.demo.data.UserDisplayDetails;
import KupAutoSklep.demo.domain.model.login.User;
import KupAutoSklep.demo.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDisplayDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDisplayDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if (user==null){
            throw new UsernameNotFoundException("Couldn't find user with that email");
        }
        return new UserDisplayDetails(user);
    }
}
