package com.arthurfialho.api.mapper;

import com.arthurfialho.api.dto.ExperienceRequestDTO;
import com.arthurfialho.api.dto.ExperienceResponseDTO;
import com.arthurfialho.api.model.Experience;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Para que o Spring possa injetá-lo
public interface ExperienceMapper {

    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    // Mapeia de Entidade para DTO de Resposta
    ExperienceResponseDTO toResponseDTO(Experience experience);

    // Mapeia de DTO de Requisição para Entidade
    Experience toEntity(ExperienceRequestDTO requestDTO);

    // Mapeia um DTO para uma Entidade existente (para atualizações)
    void updateEntityFromDto(ExperienceRequestDTO dto, @MappingTarget Experience entity);
}