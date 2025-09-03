# API de Portfólio Pessoal

Esta é uma API RESTful construída com Java e Spring Boot para gerenciar o conteúdo de um portfólio pessoal, como projetos, experiências profissionais e formação acadêmica.

## Funcionalidades

-   **Autenticação:** Sistema de login seguro baseado em JSON Web Tokens (JWT).
-   **Gerenciamento de Projetos:** Endpoints para criar, ler, atualizar e deletar (CRUD) projetos.
-   **Gerenciamento de Experiências:** Endpoints CRUD para gerenciar experiências profissionais.
-   **Gerenciamento de Educação:** Endpoints CRUD para gerenciar a formação acadêmica.

## Tecnologias Utilizadas

-   **Java 21**
-   **Spring Boot 3.5.5**
-   **Spring Web:** Para a criação de endpoints REST.
-   **Spring Data JPA:** Para a persistência de dados.
-   **Spring Security:** Para a implementação da autenticação e autorização.
-   **PostgreSQL:** Banco de dados relacional.
-   **Maven:** Para gerenciamento de dependências e build do projeto.
-   **Lombok:** Para reduzir código boilerplate (getters, setters, etc.).
-   **MapStruct:** Para mapeamento de DTOs e entidades.
-   **JJWT (Java JWT):** Para a criação e validação de tokens JWT.

## Como Começar

### Pré-requisitos

-   JDK 21 ou superior
-   Maven 3.6 ou superior
-   PostgreSQL instalado e em execução.

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-repositorio>
    cd api
    ```

2.  **Configure o Banco de Dados:**
    -   Crie um banco de dados no PostgreSQL chamado `portfolio`.
    -   Copie o arquivo `src/main/resources/application.properties.example` para `src/main/resources/application.properties`.
    -   Altere as seguintes propriedades em `application.properties` com as suas credenciais do PostgreSQL:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio
        spring.datasource.username=<seu-usuario>
        spring.datasource.password=<sua-senha>
        ```

3.  **Configure o Usuário Administrador:**
    -   Em `application.properties`, você pode definir o usuário e senha padrão que serão criados na inicialização da aplicação:
        ```properties
        admin.default.username=admin
        admin.default.password=sua-senha-segura
        ```

### Executando a Aplicação

Você pode executar a aplicação usando o Maven:

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### Executando com Docker

Uma maneira alternativa e mais prática de executar a aplicação é utilizando Docker. O projeto já vem com um arquivo `docker-compose.yml` que sobe tanto a API quanto o banco de dados PostgreSQL.

1.  **Verifique se o Docker e o Docker Compose estão instalados.**

2.  **Configure as variáveis de ambiente:**
    -   Copie o arquivo `.env.example` para `.env`.
    -   Altere as variáveis no arquivo `.env` conforme necessário, especialmente a senha do banco de dados.

3.  **Execute o Docker Compose:**
    Na raiz do projeto (`api/`), execute o seguinte comando para construir e iniciar os contêineres em modo detached:
    ```bash
    docker-compose up -d --build
    ```

A API estará disponível em `http://localhost:8080` e o banco de dados na porta `5432`.

## Endpoints da API

Todos os endpoints que manipulam dados (POST, PUT, DELETE) são protegidos e exigem um token JWT no cabeçalho `Authorization`.

### Autenticação

-   `POST /auth/login`
    -   Realiza o login e retorna um token JWT.
    -   **Corpo da Requisição:**
        ```json
        {
            "username": "admin",
            "password": "sua-senha-segura"
        }
        ```
    -   **Resposta de Sucesso:**
        ```json
        {
            "token": "seu.jwt.token"
        }
        ```

### Projetos

-   `GET /projects`: Lista todos os projetos.
-   `GET /projects/{id}`: Busca um projeto por ID.
-   `POST /projects`: Cria um novo projeto.
-   `PUT /projects/{id}`: Atualiza um projeto existente.
-   `DELETE /projects/{id}`: Deleta um projeto.

### Experiências

-   `GET /experiences`: Lista todas as experiências.
-   `POST /experiences`: Cria uma nova experiência.
-   `PUT /experiences/{id}`: Atualiza uma experiência.
-   `DELETE /experiences/{id}`: Deleta uma experiência.

### Educação

-   `GET /educations`: Lista todas as formações.
-   `POST /educations`: Cria uma nova formação.
-   `PUT /educations/{id}`: Atualiza uma formação.
-   `DELETE /educations/{id}`: Deleta uma formação.
