package com.example.paraiso.service;

import com.example.paraiso.model.Cake;
import com.example.paraiso.model.EstadoReserva;
import com.example.paraiso.model.ReservaCake;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.CakeRepository;
import com.example.paraiso.repository.ReservaCakeRepository;
import com.example.paraiso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaCakeService {

    @Autowired
    private CakeRepository cakeRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ReservaCakeRepository reservaRepo;



    public ReservaCake reservarCake(Long userId, Long cakeId, int cantidad) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cake cake = cakeRepo.findById(cakeId)
                .orElseThrow(() -> new RuntimeException("Cake no encontrado"));

        ReservaCake reserva = new ReservaCake();
        reserva.setUsuario(user);
        reserva.setCake(cake);
        reserva.setCantidadReservada(cantidad);

        return reservaRepo.save(reserva);
    }

    public ReservaCake actualizarEstado(Long reservaId, EstadoReserva nuevoEstado) {
        ReservaCake reserva = reservaRepo.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(nuevoEstado);
        return reservaRepo.save(reserva);
    }

    public List<ReservaCake> obtenerTodasLasReservas() {
        return reservaRepo.findAll();
    }


}
