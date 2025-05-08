package com.example.paraiso.service;

import com.example.paraiso.model.Cake;
import com.example.paraiso.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeService {
    @Autowired
    private CakeRepository cakeRepository;

    public Cake crearCake(Cake cake){
        // manejar errores
        cakeRepository.save(cake);
        return cake;

    }

    public List<Cake> getAllCakes(){
        return cakeRepository.findAll();
    }
}
