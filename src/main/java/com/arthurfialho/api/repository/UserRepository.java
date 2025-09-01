package com.arthurfialho.api.repository;

import com.arthurfialho.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // O Spring Data JPA criará a implementação deste método automaticamente.
    // O Spring Security usará este método para buscar o usuário pelo nome de usuário.
    Optional<User> findByUsername(String username);
}