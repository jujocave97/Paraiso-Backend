package com.example.paraiso.controller;

import com.example.paraiso.dto.ReservaCakeDTO;
import com.example.paraiso.service.AuthService;
import com.example.paraiso.service.ReservaCakeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")

public class ReservaController {

    @Autowired
    private ReservaCakeService reservaService;
    @Autowired
    private AuthService authService;

    @PutMapping("/{id}/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservaCakeDTO> cambiarEstado(
            @PathVariable String id,
            @PathVariable String estado) {
        return ResponseEntity.ok(reservaService.actualizarEstado(id, estado));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listarTodas() {
        return ResponseEntity.ok(reservaService.obtenerTodasLasReservas());
    }

    // Reservar tarta
    @PostMapping("/reservar")
    public ResponseEntity<ReservaCakeDTO> reservarCake(
            @Valid @RequestBody ReservaCakeDTO reservaCakeDTO
    ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ReservaCakeDTO reserva = reservaService.reservarCake(email,reservaCakeDTO);
        return ResponseEntity.ok(reserva);
    }



    @GetMapping("/mis-reservas")
    @PreAuthorize("hasRole('USUARIO') or hasRole('ADMIN')")
    public ResponseEntity<?> reservasUsuario(Authentication authentication) {
        String email = authentication.getName(); // el email del usuario autenticado
        if (!authService.isAdminOrSameUser(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }
        return ResponseEntity.ok(reservaService.reservasUsuario(email));
    }

   @GetMapping("/{email}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<?> resvervasUsuarioNombre(
           @PathVariable String email
   ){
        return ResponseEntity.ok(reservaService.reservasUsuario(email));
   }


    @DeleteMapping("/{reservaID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservaCakeDTO> deleteReserva(
        @PathVariable String reservaID
    ){
        ReservaCakeDTO reservaCake = reservaService.deleteReserva(reservaID);
        return ResponseEntity.ok(reservaCake);
    }

    // eliminar la reserva por parte del usuario
    @DeleteMapping("/eliminarReserva/{reservaId}")
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<?> deleteReservaRolUsuario(
            @PathVariable String reservaId, Authentication authentication
    ){
        String email = authentication.getName(); // el email del usuario autenticado
        if (!authService.isAdminOrSameUser(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }
        ReservaCakeDTO reservaCake = reservaService.deleteReservaRolUsuario(reservaId);
        return ResponseEntity.ok(reservaCake);
    }
    // hacer metodo cancelar reserva para usuarios pero si la reserva esta en curso, no se puede cancelar
}