package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserRepository extends UserRepository, JpaRepository<User, Long> {
    @Override
    User findUserByUsername(String username);

    @Override
    User findByEmail(String email);

    @Override
    boolean existsByEmail(String Email);

    @Override
    boolean existsByUsername(String existsByUsername);
}
