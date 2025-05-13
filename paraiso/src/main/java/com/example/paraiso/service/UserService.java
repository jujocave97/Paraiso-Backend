package com.example.paraiso.service;

import com.example.paraiso.dto.SignUpDTO;
import com.example.paraiso.dto.UserInformationDTO;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import com.example.paraiso.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public SignUpDTO register(SignUpDTO userDTO){
        if(userRepository.existsByEmail(userDTO.getEmail())){
            //throw new DuplicatedException("El agente ya existe");
        }

        if(userDTO.getPassword().length() > 15 || userDTO.getPassword().length() < 3){
            //throw new BadRequestException("Formato de contraseña inválido");
        }

        String password = userDTO.getPassword();
        userDTO.setPassword(encoder.encode(password));
        User user = Mapper.signUpDTOToUser(userDTO);
        userRepository.save(user);

        userDTO.setPassword("******************");
        return userDTO;
    }

    public List<UserInformationDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserInformationDTO> usersInfo= new ArrayList<>();
        for(User user: users){
            usersInfo.add(Mapper.userToUserInformation(user));
        }

        return usersInfo;
    }

}
