package com.arthurfialho.api.service;

import com.arthurfialho.api.dto.ProjectRequestDTO;
import com.arthurfialho.api.dto.ProjectResponseDTO;
import com.arthurfialho.api.exception.ResourceNotFoundException;
import com.arthurfialho.api.mapper.ProjectMapper;
import com.arthurfialho.api.model.Project;
import com.arthurfialho.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectResponseDTO> listAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toResponseDTO)
                .toList();
    }

    public ProjectResponseDTO findProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o id: " + id));
        return projectMapper.toResponseDTO(project);
    }

    public ProjectResponseDTO createProject(ProjectRequestDTO requestDTO) {
        Project project = projectMapper.toEntity(requestDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponseDTO(savedProject);
    }

    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO requestDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o id: " + id));
        projectMapper.updateEntityFromDto(requestDTO, existingProject);
        Project updatedProject = projectRepository.save(existingProject);
        return projectMapper.toResponseDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o id: " + id);
        }
        projectRepository.deleteById(id);
    }
}