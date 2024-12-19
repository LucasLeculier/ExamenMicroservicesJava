FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/ServiceRendezVous-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8093

CMD ["java","-jar","app.jar"]