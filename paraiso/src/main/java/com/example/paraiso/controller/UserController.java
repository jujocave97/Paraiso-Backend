package com.example.paraiso.controller;

import com.example.paraiso.dto.AuthRequest;
import com.example.paraiso.dto.SignUpDTO;
import com.example.paraiso.dto.UserInformationDTO;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import com.example.paraiso.security.JwtUtil;
import com.example.paraiso.service.AuthService;
import com.example.paraiso.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity<?> allUsers(){
        List<UserInformationDTO> userInformationDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userInformationDTOS);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserEmail(
            @PathVariable String email
    ){
        if (!authService.isAdminOrSameUser(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }

        UserInformationDTO userInformationDTO = userService.getUser(email);
        return ResponseEntity.ok(userInformationDTO);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(
            @PathVariable String email, @Valid @RequestBody UserInformationDTO userInformationDTO
    ){
        if (!authService.isAdminOrSameUser(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }
        UserInformationDTO userUpdated = userService.updateUser(email,userInformationDTO);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUSer(
            @PathVariable String email
    ){
        if (!authService.isAdminOrSameUser(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }
        return ResponseEntity.ok(userService.deleteUser(email));
    }

}
