package com.example.paraiso.controller;

import com.example.paraiso.dto.AuthRequest;
import com.example.paraiso.dto.SignUpDTO;
import com.example.paraiso.dto.UserInformationDTO;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import com.example.paraiso.security.JwtUtil;
import com.example.paraiso.service.UserService;
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

    @GetMapping("/") // solo admin
    public ResponseEntity<?> allUsers(){
        List<UserInformationDTO> userInformationDTOS = userService.getAllUsers();
        return ResponseEntity.ok(userInformationDTOS);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserEmail(
            @PathVariable String email
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String emailAuth = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !emailAuth.equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado");
        }

        UserInformationDTO userInformationDTO = userService.getUser(email);
        return ResponseEntity.ok(userInformationDTO);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(
            @PathVariable String email, @RequestBody UserInformationDTO userInformationDTO
    ){
        UserInformationDTO userUpdated = userService.updateUser(email,userInformationDTO);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUSer(
            @PathVariable String email
    ){
        return ResponseEntity.ok(userService.deleteUser(email));
    }

    // probar y privatizar rutas
}
