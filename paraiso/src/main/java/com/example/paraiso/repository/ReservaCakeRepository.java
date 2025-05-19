package com.example.paraiso.repository;

import com.example.paraiso.model.ReservaCake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaCakeRepository extends JpaRepository<ReservaCake, Long> {
    List<ReservaCake> findByUsuarioId(Long id);

}