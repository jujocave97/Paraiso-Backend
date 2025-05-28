package com.example.paraiso.service;

import com.example.paraiso.dto.CakeDTO;
import com.example.paraiso.exception.CakeNoEncontradoException;
import com.example.paraiso.exception.DatosInvalidosException;
import com.example.paraiso.model.Cake;
import com.example.paraiso.repository.CakeRepository;
import com.example.paraiso.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CakeService {
    @Autowired
    private CakeRepository cakeRepository;

    public CakeDTO crearCake(CakeDTO cake){
        if (cake.getNombre().isBlank() || cake.getDescripcion().isBlank()) {
            throw new DatosInvalidosException("Nombre y descripción no pueden estar vacíos");
        }
        Cake newCake = Mapper.cakeDTOToCake(cake);
        cakeRepository.save(newCake);
        return cake;

    }

    public List<CakeDTO> getAllCakes(){
        List<Cake> cakeList = cakeRepository.findAll();
        List<CakeDTO> cakeDTOS = new ArrayList<>();
        for (Cake cake: cakeList){
            cakeDTOS.add(new CakeDTO(Long.toString(cake.getId()),cake.getNombre(), cake.getDescripcion()));
        }

        return cakeDTOS;
    }

    public CakeDTO updateCake(String id, CakeDTO cakeDTO){
        if(cakeDTO.getDescripcion().isEmpty() || cakeDTO.getNombre().isEmpty()){
            throw new DatosInvalidosException("Nombre y descripción no pueden estar vacíos");
        }
        Long idUpdated= Long.parseLong(id);
        Cake cake = cakeRepository.findById(idUpdated).orElseThrow(() -> new CakeNoEncontradoException("Cake con ID " + id + " no encontrado"));
        cake.setDescripcion(cakeDTO.getDescripcion());
        cake.setNombre(cakeDTO.getNombre());

        cakeRepository.save(cake);

        return cakeDTO;
    }

    public CakeDTO deleteCake (String id){
        Long idL = Long.parseLong(id);

        Cake cake = cakeRepository.findById(idL).orElseThrow(() -> new CakeNoEncontradoException("Cake con ID " + id + " no encontrado"));
        cakeRepository.delete(cake);

        return new CakeDTO(cake.getNombre(), cake.getDescripcion());
    }

    public CakeDTO getCake(String nombre){
        Cake cake = cakeRepository.findByNombre(nombre).orElseThrow(() -> new CakeNoEncontradoException("Cake con nombre " + nombre + " no encontrado"));
        return new CakeDTO(cake.getNombre(), cake.getDescripcion());
    }
}
