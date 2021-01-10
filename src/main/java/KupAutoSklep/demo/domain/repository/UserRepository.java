package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findByEmail(String email);
    boolean existsByEmail(String Email);
    boolean existsByUsername(String existsByUsername);

}
