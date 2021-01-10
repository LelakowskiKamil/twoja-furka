package KupAutoSklep.demo.domain.repository;

import KupAutoSklep.demo.domain.model.login.User;

public interface UserRepository {
    User findUserByUsername(String username);
    User findByEmail(String email);
    boolean existsByEmail(String Email);
    boolean existsByUsername(String existsByUsername);
}
