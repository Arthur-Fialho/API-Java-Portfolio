package com.arthurfialho.api.controller;

import com.arthurfialho.api.dto.ExperienceRequestDTO;
import com.arthurfialho.api.dto.ExperienceResponseDTO;
import com.arthurfialho.api.service.ExperienceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping
    public ResponseEntity<List<ExperienceResponseDTO>> listAllExperiences() {
        return ResponseEntity.ok(experienceService.listAll());
    }

    @PostMapping
    public ResponseEntity<ExperienceResponseDTO> createExperience(@RequestBody @Valid ExperienceRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(experienceService.create(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceResponseDTO> updateExperience(@PathVariable Long id, @RequestBody @Valid ExperienceRequestDTO requestDTO) {
        return ResponseEntity.ok(experienceService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}