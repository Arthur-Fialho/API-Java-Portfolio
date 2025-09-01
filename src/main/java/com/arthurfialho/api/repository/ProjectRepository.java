package com.arthurfialho.api.repository;

import com.arthurfialho.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Anotação que marca esta interface como um componente de repositório do Spring.
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // JpaRepository já fornece métodos CRUD básicos (save, findById, findAll, deleteById, etc.)
}