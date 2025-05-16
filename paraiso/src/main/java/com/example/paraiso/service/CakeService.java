package com.example.paraiso.service;

import com.example.paraiso.dto.CakeDTO;
import com.example.paraiso.model.Cake;
import com.example.paraiso.repository.CakeRepository;
import com.example.paraiso.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CakeService {
    @Autowired
    private CakeRepository cakeRepository;

    public CakeDTO crearCake(CakeDTO cake){
        // manejar errores
        Cake newCake = Mapper.cakeDTOToCake(cake);
        cakeRepository.save(newCake);
        return cake;

    }

    public List<Cake> getAllCakes(){
        return cakeRepository.findAll();
    }

    public CakeDTO updateCake(String nombre, CakeDTO cakeDTO){
        if(cakeDTO.getDescripcion().isEmpty() || cakeDTO.getNombre().isEmpty()){
            // Throw exceptions
        }
        Cake cake = cakeRepository.findByNombre(nombre).orElseThrow();
        cake.setDescripcion(cakeDTO.getDescripcion());
        cake.setNombre(cakeDTO.getNombre());
        cakeRepository.save(cake);

        return cakeDTO;
    }

    public CakeDTO deleteCake (String nombre){
        if(cakeRepository.findByNombre(nombre).isEmpty()){
            // throw error
        }

        Cake cake = cakeRepository.findByNombre(nombre).orElseThrow();
        cakeRepository.delete(cake);

        return new CakeDTO(cake.getNombre(), cake.getDescripcion());
    }

    public CakeDTO getCake(String nombre){
        if(cakeRepository.findByNombre(nombre).isEmpty()){
            //htrow error
        }

        Cake cake = cakeRepository.findByNombre(nombre).orElseThrow();
        return new CakeDTO(cake.getNombre(), cake.getDescripcion());
    }
}
