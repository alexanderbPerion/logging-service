FROM maven:3.6-jdk-11 AS build

COPY pom.xml /usr/src/app/pom.xml
RUN mvn -f /usr/src/app/pom.xml dependency:resolve dependency:resolve-plugins

COPY src /usr/src/app/src
COPY .git /usr/src/app/.git

ENV SPRING_PROFILES_ACTIVE=stage
RUN mvn -f /usr/src/app/pom.xml clean package
RUN mvn -f /usr/src/app/pom.xml jacoco:check
RUN mvn -f /usr/src/app/pom.xml sonar:sonar

FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/sonar/report-task.txt /usr/app/report-task.txt
COPY --from=build /usr/src/app/target/logging-service.jar /usr/app/logging-service.jar
COPY dd-java-agent-0.39.0.jar /usr/app/dd-java-agent.jar

EXPOSE 8080

CMD java $JAVA_AGENT \
    $JVM_OPTIONS \
    -jar /usr/app/logging-service.jar
