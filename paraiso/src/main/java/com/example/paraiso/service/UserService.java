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

        if (!isEmailValid(userDTO.getEmail())) {
            throw new IllegalArgumentException("Formato de email inválido");
        }


        String password = userDTO.getPassword();
        userDTO.setPassword(encoder.encode(password));
        User user = Mapper.signUpDTOToUser(userDTO);
        userRepository.save(user);

        userDTO.setPassword("******************");
        userDTO.setRol(user.getRol());
        return userDTO;
    }

    public boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    public List<UserInformationDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserInformationDTO> usersInfo= new ArrayList<>();
        for(User user: users){
            usersInfo.add(Mapper.userToUserInformation(user));
        }

        return usersInfo;
    }

    public UserInformationDTO getUser(String email){
        if(email.isBlank()){
            // throw error
        }

        User u = userRepository.findByEmail(email).orElseThrow(); // manejar excepcion
        return Mapper.userToUserInformation(u);
    }

    public UserInformationDTO updateUser(String email, UserInformationDTO userInfo){
        if(email.isEmpty() || email.isBlank()) {
            // throw error
        }

        if(userInfo.getNombre().isEmpty() || userInfo.getApellidos().isEmpty() || userInfo.getEmail().isEmpty() || userInfo.getTelefono().isEmpty()){
            // throw error
        }

        User u = userRepository.findByEmail(email).orElseThrow(); // manejar excepcion
        u.setNombre(userInfo.getNombre());
        u.setApellido(userInfo.getApellidos());
        u.setEmail(userInfo.getEmail());
        u.setTelefono(userInfo.getTelefono());
        userRepository.save(u);

        return userInfo;
    }

    // crear recuperacion  de password

    public UserInformationDTO deleteUser (String email){

        User u = userRepository.findByEmail(email).orElseThrow(); // manejar excepcion
        userRepository.delete(u);

        return Mapper.userToUserInformation(u);
    }
}
