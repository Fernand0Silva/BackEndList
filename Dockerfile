# Etapa 1: construir com Maven
FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: imagem final
FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
