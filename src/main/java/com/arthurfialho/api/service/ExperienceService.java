package com.arthurfialho.api.service;

import com.arthurfialho.api.dto.ExperienceRequestDTO;
import com.arthurfialho.api.dto.ExperienceResponseDTO;
import com.arthurfialho.api.exception.ResourceNotFoundException;
import com.arthurfialho.api.model.Experience;
import com.arthurfialho.api.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<ExperienceResponseDTO> listAll() {
        return experienceRepository.findAll().stream().map(this::toDto).toList();
    }

    public ExperienceResponseDTO findById(Long id) {
        Experience experience = findExperienceById(id);
        return toDto(experience);
    }

    public ExperienceResponseDTO create(ExperienceRequestDTO requestDTO) {
        Experience experience = new Experience();
        experience.setTitle(requestDTO.title());
        experience.setCompany(requestDTO.company());
        experience.setStartDate(requestDTO.startDate());
        experience.setEndDate(requestDTO.endDate());
        experience.setDescription(requestDTO.description());
        return toDto(experienceRepository.save(experience));
    }

    public ExperienceResponseDTO update(Long id, ExperienceRequestDTO requestDTO) {
        Experience experience = findExperienceById(id);
        experience.setTitle(requestDTO.title());
        experience.setCompany(requestDTO.company());
        experience.setStartDate(requestDTO.startDate());
        experience.setEndDate(requestDTO.endDate());
        experience.setDescription(requestDTO.description());
        return toDto(experienceRepository.save(experience));
    }

    public void delete(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Experiência não encontrada com o id: " + id);
        }
        experienceRepository.deleteById(id);
    }

    private Experience findExperienceById(Long id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Experiência não encontrada com o id: " + id));
    }

    private ExperienceResponseDTO toDto(Experience experience) {
        return new ExperienceResponseDTO(
                experience.getId(),
                experience.getTitle(),
                experience.getCompany(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getDescription()
        );
    }
}