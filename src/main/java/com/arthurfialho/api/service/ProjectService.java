package com.arthurfialho.api.service;

import com.arthurfialho.api.dto.ProjectRequestDTO;
import com.arthurfialho.api.dto.ProjectResponseDTO;
import com.arthurfialho.api.exception.ResourceNotFoundException;
import com.arthurfialho.api.model.Project;
import com.arthurfialho.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    @Autowired // Injeção de dependência: O Spring nos dará uma instância de ProjectRepository.
    private ProjectRepository projectRepository;

    public ProjectResponseDTO createProject(ProjectRequestDTO requestDTO) {
        // 1. Converter o DTO de requisição para uma Entidade Project
        Project newProject = new Project();
        newProject.setTitle(requestDTO.title());
        newProject.setDescription(requestDTO.description());
        newProject.setTechnologies(requestDTO.technologies());
        newProject.setRepositoryUrl(requestDTO.repositoryUrl());
        newProject.setDemoUrl(requestDTO.demoUrl());

        // 2. Salvar a nova entidade no banco de dados
        Project savedProject = projectRepository.save(newProject);

        // 3. Converter a entidade salva para um DTO de resposta e retornar
        return new ProjectResponseDTO(
                savedProject.getId(),
                savedProject.getTitle(),
                savedProject.getDescription(),
                savedProject.getTechnologies(),
                savedProject.getRepositoryUrl(),
                savedProject.getDemoUrl()
        );
    }

        public List<ProjectResponseDTO> listAllProjects() {
            // 1. Busca todos os projetos do repositório
            List<Project> projects = projectRepository.findAll();

            // 2. Converte a lista de entidades para uma lista de DTOs
            return projects.stream()
                    .map(project -> new ProjectResponseDTO(
                            project.getId(),
                            project.getTitle(),
                            project.getDescription(),
                            project.getTechnologies(),
                            project.getRepositoryUrl(),
                            project.getDemoUrl()))
                    .toList(); // .collect(Collectors.toList()) em Java < 16
        }

        // Método para buscar um projeto pelo ID
        public ProjectResponseDTO findProjectById(Long id) {
            // O findById retorna um Optional, que pode ou não conter um Project.
            // orElseThrow para lançar uma exceção se o projeto não for encontrado.
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o id: " + id));

            // Converte a entidade encontrada para DTO e retorna
            return new ProjectResponseDTO(
                    project.getId(),
                    project.getTitle(),
                    project.getDescription(),
                    project.getTechnologies(),
                    project.getRepositoryUrl(),
                    project.getDemoUrl()
            );
        }

        public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO requestDTO) {
            // 1. Busca o projeto existente no banco, se não encontrar, lança exceção
            Project existingProject = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o id: " + id));

            // 2. Atualiza os campos do projeto existente com os dados do DTO
            existingProject.setTitle(requestDTO.title());
            existingProject.setDescription(requestDTO.description());
            existingProject.setTechnologies(requestDTO.technologies());
            existingProject.setRepositoryUrl(requestDTO.repositoryUrl());
            existingProject.setDemoUrl(requestDTO.demoUrl());

            // 3. Salva o projeto atualizado. O 'save' serve tanto para criar quanto para atualizar.
            Project updatedProject = projectRepository.save(existingProject);

            // 4. Converte para DTO e retorna
            return new ProjectResponseDTO(
                    updatedProject.getId(),
                    updatedProject.getTitle(),
                    updatedProject.getDescription(),
                    updatedProject.getTechnologies(),
                    updatedProject.getRepositoryUrl(),
                    updatedProject.getDemoUrl()
        );
    }

    public void deleteProject(Long id) {
        // 1. Verifica se o projeto existe antes de tentar deletar
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o id: " + id);
        }
        // 2. Deleta o projeto
        projectRepository.deleteById(id);
    }
}
