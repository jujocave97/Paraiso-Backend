package com.example.paraiso.controller;

import com.example.paraiso.dto.ReservaCakeDTO;
import com.example.paraiso.model.EstadoReserva;
import com.example.paraiso.model.ReservaCake;
import com.example.paraiso.service.ReservaCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")

public class ReservaController {

    @Autowired
    private ReservaCakeService reservaService;

    @PutMapping("/{id}/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservaCakeDTO> cambiarEstado(
            @PathVariable String id,
            @PathVariable String estado) {
        return ResponseEntity.ok(reservaService.actualizarEstado(id, estado));
    }

    @GetMapping
    public List<ReservaCakeDTO> listarTodas() {
        return reservaService.obtenerTodasLasReservas();
    }

    // Reservar tarta
    @PostMapping("/reservar")
    public ResponseEntity<ReservaCakeDTO> reservarCake(
            @RequestBody ReservaCakeDTO reservaCakeDTO
    ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ReservaCakeDTO reserva = reservaService.reservarCake(email,reservaCakeDTO);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/reserva") // preguntar si quiere una confirmacion de la tienda para poder hacer la eliminacion
    public ResponseEntity<ReservaCake> deleteReserva(
        @RequestParam Long reservaID
    ){
        ReservaCake reservaCake = reservaService.deleteReserva(reservaID);
        return ResponseEntity.ok(reservaCake);
    }

    // hacer metodo cancelar reserva para usuarios pero si la reserva esta en curso, no se puede cancelar
}