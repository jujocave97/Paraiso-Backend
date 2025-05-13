package com.example.paraiso.controller;

import com.example.paraiso.model.EstadoReserva;
import com.example.paraiso.model.ReservaCake;
import com.example.paraiso.service.ReservaCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reservas")

public class ReservaController {

    @Autowired
    private ReservaCakeService reservaService;

    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservaCake> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoReserva estado) {
        return ResponseEntity.ok(reservaService.actualizarEstado(id, estado));
    }

    @GetMapping
    public List<ReservaCake> listarTodas() {
        return reservaService.obtenerTodasLasReservas();
    }

    // Reservar tarta
    @PostMapping("/reservar")
    public ResponseEntity<ReservaCake> reservarCake(
            @RequestParam Long userId,
            @RequestParam Long cakeId,
            @RequestParam int cantidad
    ) {
        ReservaCake reserva = reservaService.reservarCake(userId, cakeId, cantidad);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/reserva") // preguntar si quiere una confirmacion de la tienda para poder hacer la eliminacion
    public ResponseEntity<ReservaCake> deleteReserva(
        @RequestParam Long reservaID
    ){
        ReservaCake reservaCake = reservaService.deleteReserva(reservaID);
        return ResponseEntity.ok(reservaCake);
    }


}