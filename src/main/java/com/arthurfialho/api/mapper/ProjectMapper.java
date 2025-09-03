package com.arthurfialho.api.mapper;

import com.arthurfialho.api.dto.ProjectRequestDTO;
import com.arthurfialho.api.dto.ProjectResponseDTO;
import com.arthurfialho.api.model.Project;
import org.springframework.stereotype.Component;

@Component 
public class ProjectMapper { 

    // Mapeia de Entidade para DTO de Resposta
    public ProjectResponseDTO toResponseDTO(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getTechnologies(),
                project.getRepositoryUrl(),
                project.getDemoUrl()
        );
    }

    // Mapeia de DTO de Requisição para Entidade
    public Project toEntity(ProjectRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Project project = new Project();
        project.setTitle(requestDTO.title());
        project.setDescription(requestDTO.description());
        project.setTechnologies(requestDTO.technologies());
        project.setRepositoryUrl(requestDTO.repositoryUrl());
        project.setDemoUrl(requestDTO.demoUrl());
        return project;
    }

    // Mapeia um DTO para uma Entidade existente (para atualizações)
    public void updateEntityFromDto(ProjectRequestDTO dto, Project entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setTechnologies(dto.technologies());
        entity.setRepositoryUrl(dto.repositoryUrl());
        entity.setDemoUrl(dto.demoUrl());
    }
}