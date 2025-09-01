package com.arthurfialho.api.dto;

public record ProjectResponseDTO(
        Long id,
        String title,
        String description,
        String technologies,
        String repositoryUrl,
        String demoUrl
) {
}