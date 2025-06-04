package com.example.paraiso.controller;

import com.example.paraiso.dto.AuthRequest;
import com.example.paraiso.dto.SignUpDTO;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import com.example.paraiso.security.JwtUtil;
import com.example.paraiso.service.EmailService;
import com.example.paraiso.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController { // todo: crear service

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpDTO user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        User user = userRepo.findByEmail(authRequest.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(user.getEmail(), user.getRol());

        return ResponseEntity.ok(Map.of(
                "jwt", token,
                "rol", user.getRol(),
                "email", user.getEmail()
        ));
    }

    @PostMapping("/recover")
    public String recuperarPassword(@RequestParam String email) {
        Optional<User> usuarioOpt = userRepo.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            String token = UUID.randomUUID().toString(); // o genera un JWT temporal
            User usuario = usuarioOpt.get();
            usuario.setTokenRecuperacion(token);
            userRepo.save(usuario);

            String enlace = "http://localhost:5173/reset-password/" + token; // Ajusta al frontend
            String cuerpo = "Haz clic en el siguiente enlace para restablecer tu contraseña:\n" + enlace;
            emailService.enviarCorreo(email, "Recuperación de contraseña", cuerpo);

            return "Correo enviado";
        } else {
            return "Usuario no encontrado";
        }
    }

    public static class ResetPasswordRequest {
        public String token;
        public String nuevaPassword;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getNuevaPassword() {
            return nuevaPassword;
        }

        public void setNuevaPassword(String nuevaPassword) {
            this.nuevaPassword = nuevaPassword;
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            Optional<User> usuarioOpt = userRepo.findByTokenRecuperacion(request.getToken());

            if (usuarioOpt.isPresent()) {
                User usuario = usuarioOpt.get();
                usuario.setPassword(encoder.encode(request.getNuevaPassword()));
                usuario.setTokenRecuperacion(null);
                userRepo.save(usuario);
                return ResponseEntity.ok("Contraseña actualizada con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o expirado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error del servidor: " + e.getMessage());
        }
    }


}

