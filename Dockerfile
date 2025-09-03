# --- Estágio 1: Build ---
# Usamos uma imagem que já vem com Maven e JDK 21 para compilar nosso projeto.
FROM maven:3.9-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml e baixa as dependências (isso otimiza o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código-fonte
COPY src ./src

# Compila o projeto e gera o arquivo .jar, pulando os testes
RUN mvn package -DskipTests

# --- Estágio 2: Run ---
# Usamos uma imagem base muito menor, que contém apenas o Java para executar.
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia apenas o .jar compilado do estágio de build para o nosso container final
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080 para que possamos acessá-la de fora do container
EXPOSE 8080

# Comando que será executado quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]