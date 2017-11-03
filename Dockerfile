FROM openjdk:8-jdk as BUILD

RUN mkdir /workspace
WORKDIR /workspace
COPY . .

RUN ./gradlew build

FROM openjdk:8-jre as APP

EXPOSE 8080

COPY --from=BUILD /workspace/build/libs/*.jar damn-vulnerable-spring-boot-app.jar

ENTRYPOINT ["java", "-jar", "./damn-vulnerable-spring-boot-app.jar"]
