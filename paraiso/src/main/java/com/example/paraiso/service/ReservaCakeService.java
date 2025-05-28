package com.example.paraiso.service;

import com.example.paraiso.dto.ReservaCakeDTO;
import com.example.paraiso.exception.CakeNoEncontradoException;
import com.example.paraiso.exception.ReservaNoEncontradaException;
import com.example.paraiso.exception.ReservaNoPendienteException;
import com.example.paraiso.exception.UsuarioNoEncontradoException;
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
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));


        Long idL = Long.parseLong(reservaCakeDTO.getCakeId());

        Cake cake = cakeRepo.findById(idL)
                .orElseThrow(() -> new CakeNoEncontradoException("Tarta no encontrada"));

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
                .orElseThrow(() -> new ReservaNoEncontradaException("Reserva no encontrada"));

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
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));// manejar error usuairo no encontrado

        if(!authService.isAdminOrSameUser(email)){
            // throw error
        }

        List<ReservaCake> reservaCakes = reservaRepo.findByUsuarioId(user.getId());
        List<ReservaCakeDTO> reservaCakeDTOS = new ArrayList<>();

        for(ReservaCake reservaCake: reservaCakes){
            reservaCakeDTOS.add(Mapper.reservaCakeToDTO(reservaCake));
        }

        reservaCakeDTOS.sort((a, b) -> b.getFechaReserva().compareTo(a.getFechaReserva()));

        return reservaCakeDTOS;
    }


    public ReservaCakeDTO deleteReserva(String reservaId){
        Long idl = Long.parseLong(reservaId);
        ReservaCake reserva = reservaRepo.findById(idl)
                .orElseThrow(() -> new ReservaNoEncontradaException("Reserva no encontrada")); // manejar esta excepcion

        reservaRepo.delete(reserva);
        return Mapper.reservaCakeToDTO(reserva);
    }

    public ReservaCakeDTO deleteReservaRolUsuario(String reservaId){
        Long idl = Long.parseLong(reservaId);
        ReservaCake reserva = reservaRepo.findById(idl)
                .orElseThrow(() -> new ReservaNoEncontradaException("Reserva no encontrada"));

        if (!reserva.getEstado().equals(EstadoReserva.PENDIENTE)) {
            throw new ReservaNoPendienteException("Solo se pueden eliminar reservas en estado PENDIENTE");
        }

        reservaRepo.delete(reserva);
        return Mapper.reservaCakeToDTO(reserva);
    }

}
