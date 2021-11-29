FROM openjdk:11-slim
ARG JAR_FILE=build/libs/*.jar
COPY /target/investment*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]
