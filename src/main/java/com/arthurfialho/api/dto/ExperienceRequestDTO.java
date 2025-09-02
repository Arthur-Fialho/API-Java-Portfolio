package com.arthurfialho.api.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ExperienceRequestDTO(
        @NotBlank String title,
        @NotBlank String company,
        LocalDate startDate,
        LocalDate endDate,
        String description
) {}