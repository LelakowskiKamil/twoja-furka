package KupAutoSklep.demo.service;

import KupAutoSklep.demo.UserDisplayDetails;
import KupAutoSklep.demo.domain.model.login.User;
import KupAutoSklep.demo.domain.repository.SqlUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDisplayDetailsService implements UserDetailsService {
    private final SqlUserRepository sqlUserRepository;

    public UserDisplayDetailsService(SqlUserRepository sqlUserRepository) {
        this.sqlUserRepository = sqlUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = sqlUserRepository.findByEmail(s);
        if (user==null){
            throw new UsernameNotFoundException("Couldn't find user with that email");
        }
        return new UserDisplayDetails(user);
    }
}
