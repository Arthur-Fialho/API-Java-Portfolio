package com.arthurfialho.api.service;

import com.arthurfialho.api.dto.ExperienceRequestDTO;
import com.arthurfialho.api.dto.ExperienceResponseDTO;
import com.arthurfialho.api.exception.ResourceNotFoundException;
import com.arthurfialho.api.mapper.ExperienceMapper;
import com.arthurfialho.api.model.Experience;
import com.arthurfialho.api.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ExperienceMapper experienceMapper;

    public List<ExperienceResponseDTO> listAll() {
        return experienceRepository.findAll().stream()
                .map(experienceMapper::toDto)
                .toList();
    }

    public ExperienceResponseDTO findById(Long id) {
        Experience experience = findExperienceById(id);
        return experienceMapper.toDto(experience);
    }

    public ExperienceResponseDTO create(ExperienceRequestDTO requestDTO) {
        Experience experience = experienceMapper.toEntity(requestDTO);
        return experienceMapper.toDto(experienceRepository.save(experience));
    }

    public ExperienceResponseDTO update(Long id, ExperienceRequestDTO requestDTO) {
        Experience experience = findExperienceById(id);
        experienceMapper.updateEntityFromDto(requestDTO, experience);
        return experienceMapper.toDto(experienceRepository.save(experience));
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
}