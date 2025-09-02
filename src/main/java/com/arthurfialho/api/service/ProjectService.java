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

    @Autowired // Injeção de dependência: O Spring nos dará uma instância de ProjectRepository.
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    public ProjectResponseDTO createProject(ProjectRequestDTO requestDTO) {
        Project project = projectMapper.toEntity(requestDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponseDTO(savedProject);
    }

    public List<ProjectResponseDTO> listAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toResponseDTO)
                .toList();
    }

    // Método para buscar um projeto pelo ID
    public ProjectResponseDTO findProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o id: " + id));

        // A conversão agora é feita pelo mapper.
        return projectMapper.toResponseDTO(project);
    }

    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO requestDTO) {
        // A busca no banco
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o id: " + id));

        projectMapper.updateEntityFromDto(requestDTO, existingProject);

        //Salvamos a entidade que o mapper acabou de modificar
        Project updatedProject = projectRepository.save(existingProject);

        // Convertendo para DTO e retornamos
        return projectMapper.toResponseDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        // Verifica se o projeto existe antes de tentar deletar
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o id: " + id);
        }
        // Deleta o projeto
        projectRepository.deleteById(id);
    }
}
