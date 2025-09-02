package com.arthurfialho.api.controller;

import com.arthurfialho.api.dto.EducationRequestDTO;
import com.arthurfialho.api.dto.EducationResponseDTO;
import com.arthurfialho.api.service.EducationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educations")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping
    public ResponseEntity<List<EducationResponseDTO>> listAllEducations() {
        return ResponseEntity.ok(educationService.listAll());
    }

    @PostMapping
    public ResponseEntity<EducationResponseDTO> createEducation(@RequestBody @Valid EducationRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(educationService.create(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationResponseDTO> updateEducation(@PathVariable Long id, @RequestBody @Valid EducationRequestDTO requestDTO) {
        return ResponseEntity.ok(educationService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}