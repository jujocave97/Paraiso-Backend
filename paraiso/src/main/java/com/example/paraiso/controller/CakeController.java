package com.example.paraiso.controller;

import com.example.paraiso.dto.CakeDTO;
import com.example.paraiso.service.AuthService;
import com.example.paraiso.service.CakeService;
import com.example.paraiso.service.ReservaCakeService;
import jakarta.validation.Valid;
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
    @Autowired
    private AuthService authService;


    // Listar tartas
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/")
    public List<CakeDTO> getAllCakes() {
        return cakeService.getAllCakes();
    }

    // Crear tarta (ADMIN)
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CakeDTO> createCake(@Valid @RequestBody CakeDTO cake) {
        return ResponseEntity.ok(cakeService.crearCake(cake));
        // manejar errores
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCake(
            @PathVariable String id,@Valid @RequestBody CakeDTO cakeDTO
    ){
        return ResponseEntity.ok(cakeService.updateCake(id, cakeDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<?> deleteCake(
            @PathVariable String id
    ){
        return ResponseEntity.ok(cakeService.deleteCake(id));
    }
    //todo:  manejar error de que las tartas no se pueden borrar si estan reservadas

}
