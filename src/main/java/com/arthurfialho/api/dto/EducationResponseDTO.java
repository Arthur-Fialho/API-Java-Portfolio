package com.arthurfialho.api.dto;

import java.time.LocalDate;

public record EducationResponseDTO(
        Long id,
        String institution,
        String course,
        LocalDate startDate,
        LocalDate endDate,
        String description
) {}