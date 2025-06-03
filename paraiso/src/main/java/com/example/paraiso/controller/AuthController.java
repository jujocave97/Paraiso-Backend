package com.example.paraiso.controller;

import com.example.paraiso.dto.AuthRequest;
import com.example.paraiso.dto.SignUpDTO;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import com.example.paraiso.security.JwtUtil;
import com.example.paraiso.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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
}
