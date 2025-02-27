# Используем базовый образ Java
FROM openjdk:17-jdk-alpine

# Указываем рабочую директорию
WORKDIR /app

# Копируем JAR-файл приложения
COPY target/businessplanner-1.0.0.jar app.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]