package com.arthurfialho.api.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record EducationRequestDTO(
        @NotBlank String institution,
        @NotBlank String course,
        LocalDate startDate,
        LocalDate endDate,
        String description
) {}
