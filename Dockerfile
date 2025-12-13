# =========================
# STAGE 1: Construir librería e instalar dependencias
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiamos el POM para usar la cache de Docker
COPY pom.xml .

# Descargar TODAS las dependencias de forma offline
RUN mvn -B dependency:go-offline

# Copiamos el proyecto completo
COPY . .

# Compilar e INSTALAR en el repositorio local
RUN mvn -B clean install -DskipTests


# =========================
# STAGE 2: Imagen final con repo Maven pre-cargado
# =========================
FROM openjdk:17.0.1-jdk-slim

# Copiar el repo .m2 (contiene TODAS tus dependencias + tu librería ya instalada)
COPY --from=builder /root/.m2 /root/.m2

# Dejar un workspace opcional
WORKDIR /workspace

CMD ["bash"]
