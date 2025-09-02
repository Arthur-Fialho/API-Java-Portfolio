package com.arthurfialho.api.service;

import com.arthurfialho.api.dto.ProjectRequestDTO;
import com.arthurfialho.api.dto.ProjectResponseDTO;
import com.arthurfialho.api.exception.ResourceNotFoundException;
import com.arthurfialho.api.mapper.ProjectMapper;
import com.arthurfialho.api.model.Project;
import com.arthurfialho.api.repository.ProjectRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Assertions;

import java.util.Optional;


// Habilita a integração do Mockito com o JUnit 5
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    // @Mock: Cria uma versão "fake" (um dublê) da classe.
    // Nós controlaremos o comportamento deste objeto.
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    // @InjectMocks: Cria uma instância real de ProjectService
    // e injeta os @Mocks (projectRepository e projectMapper) nela.
    @InjectMocks
    private ProjectService projectService;

    // Nossos métodos de teste virão aqui...

    @Test
    void testCreateProject_WithValidData_ShouldReturnProjectResponseDTO() {
        // Arrange (Organizar) - Preparamos o cenário do teste
        
        // 1. Criamos os objetos de entrada e saída esperados
        var requestDTO = new ProjectRequestDTO("API Teste", "Descrição", "Java", null, null);
        var project = new Project(); // A entidade que o repository.save deveria receber
        var expectedResponseDTO = new ProjectResponseDTO(1L, "API Teste", "Descrição", "Java", null, null);

        // 2. Ensinamos aos nossos Mocks como se comportar
        // "Quando o mapper.toEntity for chamado com nosso requestDTO, retorne o objeto project"
        when(projectMapper.toEntity(requestDTO)).thenReturn(project);
        // "Quando o repository.save for chamado com QUALQUER objeto da classe Project, retorne esse mesmo objeto"
        when(projectRepository.save(any(Project.class))).thenReturn(project);
        // "Quando o mapper.toResponseDTO for chamado, retorne nosso DTO de resposta esperado"
        when(projectMapper.toResponseDTO(project)).thenReturn(expectedResponseDTO);

        // Act (Agir) - Executamos o método que queremos testar
        ProjectResponseDTO actualResponse = projectService.createProject(requestDTO);

        // Assert (Verificar) - Checamos se o resultado foi o esperado
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponseDTO.id(), actualResponse.id());
        Assertions.assertEquals(expectedResponseDTO.title(), actualResponse.title());
    }

    @Test
    void testFindProjectById_WhenProjectDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Arrange
        // Ensinamos ao repositório que, quando findById for chamado com qualquer Long,
        // ele deve retornar um Optional vazio, simulando que o projeto não foi encontrado.
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        // Verificamos se a execução de projectService.findProjectById lança a exceção que esperamos.
        // O teste só passa se a exceção ResourceNotFoundException for lançada.
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            projectService.findProjectById(99L);
        });
    }
}