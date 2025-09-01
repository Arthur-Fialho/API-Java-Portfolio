package com.arthurfialho.api.config;

import com.arthurfialho.api.model.User;
import com.arthurfialho.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // Importe esta classe
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.default.username}")
    private String adminUsername;

    @Value("${admin.default.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se o usuário já existe usando a variável injetada
        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            User admin = new User();
            admin.setUsername(adminUsername);
            // Usa a senha injetada para codificar e salvar
            admin.setPassword(passwordEncoder.encode(adminPassword));

            userRepository.save(admin);
            System.out.println(">>> Usuário '" + adminUsername + "' criado com sucesso!");
        }
    }
}