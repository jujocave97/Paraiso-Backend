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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReservaCakeService {

    @Autowired
    private CakeRepository cakeRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ReservaCakeRepository reservaRepo;
    @Autowired
    private AuthService authService;



    public ReservaCakeDTO reservarCake(String email,ReservaCakeDTO reservaCakeDTO) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        Long idL = Long.parseLong(reservaCakeDTO.getCakeId());

        Cake cake = cakeRepo.findById(idL)
                .orElseThrow(() -> new RuntimeException("Cake no encontrado"));

        ReservaCake reserva = new ReservaCake();
        reserva.setUsuario(user);
        reserva.setCake(cake);
        reserva.setCantidadReservada(reservaCakeDTO.getCantidad());
        reserva.setComentario(reservaCakeDTO.getComentario());
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

    public List<ReservaCakeDTO> reservasUsuario (String email){
        User user = userRepo.findByEmail(email).orElseThrow();// manejar error usuairo no encontrado

        if(!authService.isAdminOrSameUser(email)){
            // throw error
        }

        List<ReservaCake> reservaCakes = reservaRepo.findByUsuarioId(user.getId());
        List<ReservaCakeDTO> reservaCakeDTOS = new ArrayList<>();

        for(ReservaCake reservaCake: reservaCakes){
            reservaCakeDTOS.add(Mapper.reservaCakeToDTO(reservaCake));
        }

        return reservaCakeDTOS;
    }

    public ReservaCakeDTO deleteReserva(String reservaId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long idl = Long.parseLong(reservaId);

        if(authService.isAdminOrSameUser(email)){
            // throw exception
        }

        ReservaCake reserva = reservaRepo.findById(idl)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada")); // manejar esta excepcion

        reservaRepo.delete(reserva);
        return Mapper.reservaCakeToDTO(reserva);
    }

}
