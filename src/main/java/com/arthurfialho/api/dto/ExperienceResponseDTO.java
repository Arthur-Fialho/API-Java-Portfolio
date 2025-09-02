package com.arthurfialho.api.dto;

import java.time.LocalDate;

public record ExperienceResponseDTO(
        Long id,
        String title,
        String company,
        LocalDate startDate,
        LocalDate endDate,
        String description
) {}