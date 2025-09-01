package com.arthurfialho.api.model;

import jakarta.persistence.*;
import lombok.Data; // Importa a anotação @Data do Lombok

@Entity // Anotação que diz ao JPA: "Esta classe é uma entidade e corresponde a uma tabela no banco de dados"
@Table(name = "projects") // Especifica o nome da tabela no banco. Se omitido, seria "project".
@Data // Anotação do Lombok que gera automaticamente getters, setters, toString, equals e hashCode.
public class Project {

    @Id // Marca este campo como a chave primária (identificador único) da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco de dados para gerar o valor do ID automaticamente (autoincremento).
    private Long id;

    @Column(nullable = false) // Mapeia para uma coluna na tabela. 'nullable = false' significa que não pode ser nula.
    private String title;

    @Column(length = 500) // Define o tamanho máximo da coluna de texto.
    private String description;

    private String technologies; // Se a anotação @Column for omitida, o JPA usa configurações padrão.

    private String repositoryUrl;

    private String demoUrl;
}