package com.lelakowsky.twojafurka.repository;

import com.lelakowsky.twojafurka.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    boolean existsByEmail(String Email);
    boolean existsByUsername(String existsByUsername);
    User findById(long id);
}
