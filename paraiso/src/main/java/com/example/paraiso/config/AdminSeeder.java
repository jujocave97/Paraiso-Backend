package com.example.paraiso.config;

import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class AdminSeeder implements CommandLineRunner {

    @Value("${admin.email}")
    private String email;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.nombre}")
    private String nombre;

    @Value("${admin.apellido}")
    private String apellido;

    @Value("${admin.telefono}")
    private String telefono;

    @Value("${admin.rol}")
    private String rol;

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepo.existsByEmail(email)) {
            User admin = new User();
            admin.setEmail(email);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setNombre(nombre);
            admin.setApellido(apellido);
            admin.setTelefono(telefono);
            admin.setRol(rol);

            userRepo.save(admin);
            System.out.println("✅ Usuario ADMIN creado por seed");
        } else {
            System.out.println("ℹ️ Admin ya existe, no se crea de nuevo");
        }
    }
}
