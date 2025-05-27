package com.example.paraiso.repository;

import com.example.paraiso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByResetPasswordToken(String token);
    Optional<User> findByNombre(String nombre);
    boolean existsByEmail(String email);
}
