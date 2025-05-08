package com.example.paraiso.controller;

import com.example.paraiso.dto.AuthRequest;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import com.example.paraiso.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController { // todo: crear service

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email ya registrado");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRol("USUARIO");
        return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        User user = userRepo.findByEmail(authRequest.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
                "jwt", token,
                "rol", user.getRol(),
                "email", user.getEmail()
        ));
    }
}
