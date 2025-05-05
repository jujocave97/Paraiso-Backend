package com.example.paraiso.repository;

import com.example.paraiso.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, Long> {}