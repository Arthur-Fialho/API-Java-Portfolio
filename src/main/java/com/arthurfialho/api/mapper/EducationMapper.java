package com.arthurfialho.api.mapper;

import com.arthurfialho.api.dto.EducationRequestDTO;
import com.arthurfialho.api.dto.EducationResponseDTO;
import com.arthurfialho.api.model.Education;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Para que o Spring possa injetá-lo
public interface EducationMapper {
    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    // Mapeia de Entidade para DTO de Resposta
    EducationResponseDTO toResponseDTO(Education education);

    // Mapeia de DTO de Requisição para Entidade
    Education toEntity(EducationRequestDTO requestDTO);

    // Mapeia um DTO para uma Entidade existente (para atualizações)
    void updateEntityFromDto(EducationRequestDTO dto, @MappingTarget Education entity);
}