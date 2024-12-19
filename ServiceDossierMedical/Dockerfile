FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/ServiceDossierMedical-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8092

CMD ["java","-jar","app.jar"]