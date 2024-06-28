# Usar una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /pizzeriaDominos

# Copiar el archivo JAR de la aplicación del local al contenedor COPY <origen> <destino>
COPY build/libs/pizzeriaDominos-1.0.jar pizzeriaDominos.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "pizzeriaDominos.jar"]