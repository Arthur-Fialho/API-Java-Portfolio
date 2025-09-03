package com.arthurfialho.api.mapper;

import com.arthurfialho.api.dto.EducationRequestDTO;
import com.arthurfialho.api.dto.EducationResponseDTO;
import com.arthurfialho.api.model.Education;
import org.springframework.stereotype.Component;

@Component 
public class EducationMapper { 

    // Mapeia de Entidade para DTO de Resposta
    public EducationResponseDTO toDto(Education education) {
        if (education == null) {
            return null;
        }
        return new EducationResponseDTO(
                education.getId(),
                education.getInstitution(),
                education.getCourse(),
                education.getStartDate(),
                education.getEndDate(),
                education.getDescription()
        );
    }

    // Mapeia de DTO de Requisição para Entidade
    public Education toEntity(EducationRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Education education = new Education();
        education.setInstitution(requestDTO.institution());
        education.setCourse(requestDTO.course());
        education.setStartDate(requestDTO.startDate());
        education.setEndDate(requestDTO.endDate());
        education.setDescription(requestDTO.description());
        return education;
    }

    // Mapeia um DTO para uma Entidade existente (para atualizações)
    public void updateEntityFromDto(EducationRequestDTO dto, Education entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setInstitution(dto.institution());
        entity.setCourse(dto.course());
        entity.setStartDate(dto.startDate());
        entity.setEndDate(dto.endDate());
        entity.setDescription(dto.description());
    }
}