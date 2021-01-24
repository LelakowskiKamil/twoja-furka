package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.login.User;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {
    User findUserByUsername(String username);
    User findByEmail(String email);
    boolean existsByEmail(String Email);
    boolean existsByUsername(String existsByUsername);

    User save(User user);

    User findById(long id);
}
