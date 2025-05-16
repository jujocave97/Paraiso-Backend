package com.example.paraiso.controller;

import com.example.paraiso.dto.CakeDTO;
import com.example.paraiso.model.Cake;
import com.example.paraiso.model.EstadoReserva;
import com.example.paraiso.model.ReservaCake;
import com.example.paraiso.repository.CakeRepository;
import com.example.paraiso.service.CakeService;
import com.example.paraiso.service.ReservaCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cakes")
public class CakeController {  // todo: crear service

    @Autowired
    private CakeService cakeService;
    @Autowired
    private ReservaCakeService reservaService;


    // Listar tartas
    @GetMapping
    public List<Cake> getAllCakes() {
        return cakeService.getAllCakes();
    }

    // Crear tarta (ADMIN)
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CakeDTO> createCake(@RequestBody CakeDTO cake) {
        return ResponseEntity.ok(cakeService.crearCake(cake));
        // manejar errores
    }


}
