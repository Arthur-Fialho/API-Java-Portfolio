package com.arthurfialho.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "educations")
@Data
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String institution; // Instituição

    @Column(nullable = false)
    private String course; // Curso

    private LocalDate startDate;

    private LocalDate endDate; // Pode ser nulo se for o curso atual

    @Column(length = 1000)
    private String description;
}