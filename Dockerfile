FROM openjdk:17-alpine
ADD target/farmuInterview-1.0.0.jar farmuInterview-1.0.0.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "farmuInterview-1.0.0.jar"]