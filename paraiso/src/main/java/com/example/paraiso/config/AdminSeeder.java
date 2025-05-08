package com.example.paraiso.config;

import com.example.paraiso.model.User;
import com.example.paraiso.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepo.existsByEmail("admin@paraiso.com")) {
            User admin = new User();
            admin.setEmail("admin@paraiso.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // 🔐 segura
            admin.setNombre("Super");
            admin.setApellido("Admin");
            admin.setTelefono("999999999");
            admin.setRol("ADMIN");

            userRepo.save(admin);
            System.out.println("✅ Usuario ADMIN creado por seed");
        } else {
            System.out.println("ℹ️ Admin ya existe, no se crea de nuevo");
        }
    }
}
