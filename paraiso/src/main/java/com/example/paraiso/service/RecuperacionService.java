package com.example.paraiso.service;

import com.example.paraiso.model.PasswordResetToken;
import com.example.paraiso.model.User;
import com.example.paraiso.repository.PasswordResetTokenRepository;
import com.example.paraiso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecuperacionService {

    @Autowired
    private UserRepository usuarioRepo;

    @Autowired
    private PasswordResetTokenRepository tokenRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder encoder;

    public void solicitarResetPassword(String email) throws IOException {
        Optional<User> optional = usuarioRepo.findByEmail(email);
        if (optional.isEmpty()) return; // No lanzas error por seguridad

        User usuario = optional.get();
        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUsuario(usuario);
        resetToken.setExpiracion(LocalDateTime.now().plusHours(1));
        tokenRepo.save(resetToken);

        String link = "http://localhost:3000/reset-password?token=" + token;
        String mensaje = "Haz clic en el siguiente enlace para restablecer tu contraseña:\n\n" + link;

        emailService.enviarCorreo(email, "Recuperar contraseña", mensaje);
    }

    public boolean restablecerPassword(String token, String nuevaPass) {
        Optional<PasswordResetToken> optional = tokenRepo.findByToken(token);
        if (optional.isEmpty()) return false;

        PasswordResetToken resetToken = optional.get();
        if (resetToken.getExpiracion().isBefore(LocalDateTime.now())) return false;

        User usuario = resetToken.getUsuario();
        usuario.setPassword(encoder.encode(nuevaPass));
        usuarioRepo.save(usuario);

        tokenRepo.delete(resetToken);
        return true;
    }
}
