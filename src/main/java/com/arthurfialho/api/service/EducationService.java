package com.arthurfialho.api.service;

import com.arthurfialho.api.mapper.EducationMapper;
import com.arthurfialho.api.dto.EducationRequestDTO;
import com.arthurfialho.api.dto.EducationResponseDTO;
import com.arthurfialho.api.model.Education;
import com.arthurfialho.api.repository.EducationRepository;
import com.arthurfialho.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private EducationMapper educationMapper;

    public List<EducationResponseDTO> listAll() {
        return educationRepository.findAll().stream()
                .map(educationMapper::toResponseDTO)
                .toList();
    }

    public EducationResponseDTO findById(Long id) {

        Education education = findEducationById(id);
        return educationMapper.toResponseDTO(education);
    }

    public EducationResponseDTO create(EducationRequestDTO requestDTO) {
        Education education = educationMapper.toEntity(requestDTO);
        return educationMapper.toResponseDTO(educationRepository.save(education));
    }

    public EducationResponseDTO update(Long id, EducationRequestDTO requestDTO) {
        Education education = findEducationById(id);
        educationMapper.updateEntityFromDto(requestDTO, education);
        return educationMapper.toResponseDTO(educationRepository.save(education));
    }

    public void delete(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Educação não encontrada com o id: " + id);
        }
        educationRepository.deleteById(id);
    }

    private Education findEducationById(Long id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Educação não encontrada com o id: " + id));
    }
}