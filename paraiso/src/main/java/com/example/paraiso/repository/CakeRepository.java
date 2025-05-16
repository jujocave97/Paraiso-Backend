package com.example.paraiso.repository;

import com.example.paraiso.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CakeRepository extends JpaRepository<Cake, Long> {
    Optional<Cake> findByNombre(String nombre);
}