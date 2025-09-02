package com.arthurfialho.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "experiences")
@Data
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // Cargo

    @Column(nullable = false)
    private String company; // Empresa

    private LocalDate startDate;

    private LocalDate endDate; // Pode ser nulo se for o emprego atual

    @Column(length = 1000)
    private String description;
}