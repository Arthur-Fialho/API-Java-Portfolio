package com.arthurfialho.api.controller;

import com.arthurfialho.api.dto.LoginRequestDTO;
import com.arthurfialho.api.dto.LoginResponseDTO;
import com.arthurfialho.api.model.User;
import com.arthurfialho.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        // 1. Cria um objeto de autenticação com o usuário e senha recebidos
        var authToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        // 2. O AuthenticationManager irá validar as credenciais.
        // Ele usará o nosso UserRepository (via um UserDetailsService interno) e o PasswordEncoder.
        // Se as credenciais forem inválidas, ele lançará uma exceção (tratada pelo Spring).
        Authentication authentication = authenticationManager.authenticate(authToken);

        // 3. Se a autenticação for bem-sucedida, pega o objeto User e gera o token
        User authenticatedUser = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(authenticatedUser);

        // 4. Retorna o token em um DTO de resposta
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}