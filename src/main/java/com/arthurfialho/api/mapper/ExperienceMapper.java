package com.arthurfialho.api.mapper;

import com.arthurfialho.api.dto.ExperienceRequestDTO;
import com.arthurfialho.api.dto.ExperienceResponseDTO;
import com.arthurfialho.api.model.Experience;
import org.springframework.stereotype.Component;

@Component 
public class ExperienceMapper { 

    // Mapeia de Entidade para DTO de Resposta
    public ExperienceResponseDTO toDto(Experience experience) {
        if (experience == null) {
            return null;
        }
        return new ExperienceResponseDTO(
                experience.getId(),
                experience.getTitle(),
                experience.getCompany(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getDescription()
        );
    }

    // Mapeia de DTO de Requisição para Entidade
    public Experience toEntity(ExperienceRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Experience experience = new Experience();
        experience.setTitle(requestDTO.title());
        experience.setCompany(requestDTO.company());
        experience.setStartDate(requestDTO.startDate());
        experience.setEndDate(requestDTO.endDate());
        experience.setDescription(requestDTO.description());
        return experience;
    }

    // Mapeia um DTO para uma Entidade existente (para atualizações)
    public void updateEntityFromDto(ExperienceRequestDTO dto, Experience entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setTitle(dto.title());
        entity.setCompany(dto.company());
        entity.setStartDate(dto.startDate());
        entity.setEndDate(dto.endDate());
        entity.setDescription(dto.description());
    }
}