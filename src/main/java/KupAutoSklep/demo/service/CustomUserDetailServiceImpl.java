package KupAutoSklep.demo.service;

import KupAutoSklep.demo.domain.repository.SqlUserRepository;
import KupAutoSklep.demo.domain.model.login.Role;
import KupAutoSklep.demo.domain.model.login.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

private final SqlUserRepository sqlUserRepository;

    public CustomUserDetailServiceImpl(SqlUserRepository sqlUserRepository) {
        this.sqlUserRepository = sqlUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = sqlUserRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


}
