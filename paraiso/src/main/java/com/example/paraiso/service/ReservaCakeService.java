package com.example.paraiso.service;

import com.example.paraiso.dto.ReservaCakeDTO;
import com.example.paraiso.model.Cake;
import com.example.paraiso.model.EstadoReserva;
import com.example.paraiso.model.ReservaCake;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.CakeRepository;
import com.example.paraiso.repository.ReservaCakeRepository;
import com.example.paraiso.repository.UserRepository;
import com.example.paraiso.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaCakeService {

    @Autowired
    private CakeRepository cakeRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ReservaCakeRepository reservaRepo;



    public ReservaCakeDTO reservarCake(String email,ReservaCakeDTO reservaCakeDTO) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        Long idL = Long.parseLong(reservaCakeDTO.getCake());

        Cake cake = cakeRepo.findById(idL)
                .orElseThrow(() -> new RuntimeException("Cake no encontrado"));

        ReservaCake reserva = new ReservaCake();
        reserva.setUsuario(user);
        reserva.setCake(cake);
        reserva.setCantidadReservada(reservaCakeDTO.getCantidad());
        reservaRepo.save(reserva);


        return Mapper.reservaCakeToDTO(reserva);
    }

    public ReservaCakeDTO actualizarEstado(String reservaId, String nuevoEstado) {
        Long idL = Long.parseLong(reservaId);
        ReservaCake reserva = reservaRepo.findById(idL)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(EstadoReserva.valueOf(nuevoEstado));
        reservaRepo.save(reserva);

        return Mapper.reservaCakeToDTO(reserva);
    }

    public List<ReservaCakeDTO> obtenerTodasLasReservas() {
        List<ReservaCake> reservas = reservaRepo.findAll();
        List<ReservaCakeDTO> dtos = new ArrayList<>();

        for(ReservaCake reservaCake: reservas){
            dtos.add(Mapper.reservaCakeToDTO(reservaCake));
        }

        return dtos;
    }

    public ReservaCake deleteReserva(Long reservaId){
        ReservaCake reserva = reservaRepo.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reservaRepo.delete(reserva);
        return reserva;
    }

}
