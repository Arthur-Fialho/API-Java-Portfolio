package com.arthurfialho.api.mapper;

import com.arthurfialho.api.dto.ProjectRequestDTO;
import com.arthurfialho.api.dto.ProjectResponseDTO;
import com.arthurfialho.api.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Faz o MapStruct gerar um Bean do Spring
public interface ProjectMapper {

    // Instância do mapper que podemos usar (opcional com componentModel="spring", mas útil)
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    // Mapeia de Entidade para DTO de Resposta
    ProjectResponseDTO toResponseDTO(Project project);

    // Mapeia de DTO de Requisição para Entidade
    Project toEntity(ProjectRequestDTO projectRequestDTO);

    void updateEntityFromDto(ProjectRequestDTO dto, @MappingTarget Project entity);
}