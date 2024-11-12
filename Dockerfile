# Utiliser une image de base Java (par exemple OpenJDK 17)
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de votre application dans l'image Docker
# Remplacez 'monapp.jar' par le nom de votre fichier JAR
COPY target/monapp.jar app.jar

# Exposer le port (changer le numéro de port si nécessaire)
EXPOSE 8080

# Définir la commande de lancement de l'application
CMD ["java", "-jar", "app.jar"]
