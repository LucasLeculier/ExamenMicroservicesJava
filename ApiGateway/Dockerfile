FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/ApiGateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8095

CMD ["java","-jar","app.jar"]