FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/ServicePracticien-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8091

CMD ["java","-jar","app.jar"]