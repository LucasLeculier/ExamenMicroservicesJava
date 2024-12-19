FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/ServicePatient-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

CMD ["java","-jar","app.jar"]