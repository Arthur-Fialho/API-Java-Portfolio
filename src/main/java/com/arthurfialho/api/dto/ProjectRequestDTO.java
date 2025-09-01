package com.arthurfialho.api.dto;

import jakarta.validation.constraints.NotBlank;

// O record já gera getters, construtor, equals, hashCode e toString.
public record ProjectRequestDTO(
        @NotBlank // Garante que o campo não seja nulo nem vazio
        String title,
        String description,
        String technologies,
        String repositoryUrl,
        String demoUrl
) {
}
