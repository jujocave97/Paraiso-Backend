package com.example.paraiso.util;

import com.example.paraiso.dto.CakeDTO;
import com.example.paraiso.dto.SignUpDTO;
import com.example.paraiso.dto.UserInformationDTO;
import com.example.paraiso.model.Cake;
import com.example.paraiso.model.User;


public class Mapper {
    public static User signUpDTOToUser(SignUpDTO userDTO){
        User user = new User();
        user.setNombre(userDTO.getNombre());
        user.setApellido(userDTO.getApellidos());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setTelefono(userDTO.getTelefono());
        user.setRol("USUARIO");
        return user;
    }

    public static UserInformationDTO userToUserInformation(User user){
        UserInformationDTO userInfo = new UserInformationDTO(
                user.getNombre(),
                user.getApellido(),
                user.getTelefono(),
                user.getEmail(),
                user.getRol()
        );
        return userInfo;
    }

    public static Cake cakeDTOToCake (CakeDTO cake){
        Cake newCake = new Cake();
        newCake.setNombre(cake.getNombre());
        newCake.setDescripcion(cake.getDescripcion());
        return newCake;
    }
}
