# Etapa de construção
FROM maven:3.9.9-amazoncorretto-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:21-ea-10-jdk-slim
WORKDIR /app
COPY --from=build /app/target/AppRH-0.0.1-SNAPSHOT.jar ./AppRH-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "--enable-preview", "-jar", "AppRH-0.0.1-SNAPSHOT.jar"]



