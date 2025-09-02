package com.arthurfialho.api.service;

import com.arthurfialho.api.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtParserBuilder;    
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        // Gera a chave secreta a partir da string no application.properties
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Instant now = Instant.now();
        // Define o tempo de expiração para 2 horas a partir de agora
        Instant expiration = now.plusSeconds(7200); // 2 horas * 3600 segundos

        return Jwts.builder()
                .setIssuer("Portfolio API") // Emissor do token
                .setSubject(user.getUsername()) // Assunto do token (quem ele representa)
                .setIssuedAt(Date.from(now)) // Data de emissão
                .setExpiration(Date.from(expiration)) // Data de expiração
                .signWith(key) // Assina o token com a chave secreta
                .compact(); // Constrói o token e o serializa para uma string
    }

    public String validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

        } catch (Exception e) {
            // Se o token for inválido, retorna null.
            return null;
        }
    }
}