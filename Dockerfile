FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/paraiso_backend-0.0.1.jar
COPY ${JAR_FILE} app_paraiso.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_paraiso.jar"]