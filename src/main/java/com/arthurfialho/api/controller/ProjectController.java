package com.arthurfialho.api.controller;

import com.arthurfialho.api.dto.ProjectRequestDTO;
import com.arthurfialho.api.dto.ProjectResponseDTO;
import com.arthurfialho.api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Combina @Controller e @ResponseBody, simplificando a criação de APIs REST.
@RequestMapping("/projects") // Todas as requisições para /projects serão tratadas por este controller.
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping // Mapeia requisições HTTP POST para este método.
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody @Valid ProjectRequestDTO requestDTO) {
        ProjectResponseDTO createdProject = projectService.createProject(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // Endpoint para listar todos os projetos
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> listAllProjects() {
        List<ProjectResponseDTO> projects = projectService.listAllProjects();
        return ResponseEntity.ok(projects);
    }

    // Endpoint para buscar um projeto pelo ID
    // {id} é uma variável na URL
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> findProjectById(@PathVariable Long id) {
        // @PathVariable associa a variável {id} da URL ao parâmetro Long id do método
        ProjectResponseDTO project = projectService.findProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequestDTO requestDTO) {
        ProjectResponseDTO updatedProject = projectService.updateProject(id, requestDTO);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        // Para delete, a convenção é retornar 204 No Content, que significa "sucesso, sem conteúdo para devolver".
        return ResponseEntity.noContent().build();
    }
}