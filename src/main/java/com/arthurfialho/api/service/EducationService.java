package com.arthurfialho.api.service;

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

    public List<EducationResponseDTO> listAll() {
        return educationRepository.findAll().stream().map(this::toDto).toList();
    }

    public EducationResponseDTO findById(Long id) {
        Education education = findEducationById(id);
        return toDto(education);
    }

    public EducationResponseDTO create(EducationRequestDTO requestDTO) {
        Education education = new Education();
        education.setInstitution(requestDTO.institution());
        education.setCourse(requestDTO.course());
        education.setStartDate(requestDTO.startDate());
        education.setEndDate(requestDTO.endDate());
        education.setDescription(requestDTO.description());
        return toDto(educationRepository.save(education));
    }

    public EducationResponseDTO update(Long id, EducationRequestDTO requestDTO) {
        Education education = findEducationById(id);
        education.setInstitution(requestDTO.institution());
        education.setCourse(requestDTO.course());
        education.setStartDate(requestDTO.startDate());
        education.setEndDate(requestDTO.endDate());
        education.setDescription(requestDTO.description());
        return toDto(educationRepository.save(education));
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

    private EducationResponseDTO toDto(Education education) {
        return new EducationResponseDTO(
                education.getId(),
                education.getInstitution(),
                education.getCourse(),
                education.getStartDate(),
                education.getEndDate(),
                education.getDescription()
        );
    }
}