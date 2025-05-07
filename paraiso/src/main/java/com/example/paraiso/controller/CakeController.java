package com.example.paraiso.controller;

import com.example.paraiso.model.Cake;
import com.example.paraiso.model.EstadoReserva;
import com.example.paraiso.model.ReservaCake;
import com.example.paraiso.repository.CakeRepository;
import com.example.paraiso.service.ReservaCakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cakes")
public class CakeController {  // todo: crear service

    private final CakeRepository cakeRepo;
    private final ReservaCakeService reservaService;

    public CakeController(CakeRepository cakeRepo, ReservaCakeService reservaService) {
        this.cakeRepo = cakeRepo;
        this.reservaService = reservaService;
    }

    // Listar tartas
    @GetMapping
    public List<Cake> getAllCakes() {
        return cakeRepo.findAll();
    }

    // Crear tarta (ADMIN)
    @PostMapping("/admin")
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {
        return ResponseEntity.ok(cakeRepo.save(cake));
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

    @RestController
    @RequestMapping("/api/admin/reservas")
    @PreAuthorize("hasRole('ADMIN')")
    public class AdminReservaController {

        private final ReservaCakeService reservaService;

        public AdminReservaController(ReservaCakeService reservaService) {
            this.reservaService = reservaService;
        }

        @PutMapping("/{id}/estado")
        public ResponseEntity<ReservaCake> cambiarEstado(
                @PathVariable Long id,
                @RequestParam EstadoReserva estado) {
            return ResponseEntity.ok(reservaService.actualizarEstado(id, estado));
        }

        @GetMapping
        public List<ReservaCake> listarTodas() {
            return reservaService.obtenerTodasLasReservas();
        }
    }

}
