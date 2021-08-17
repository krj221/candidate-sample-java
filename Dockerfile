
FROM maven:3.8-jdk-11 as builder


WORKDIR /app
COPY pom.xml .
COPY src ./src


RUN mvn package -DskipTests


FROM adoptopenjdk/openjdk11:alpine-jre


COPY --from=builder /app/target/candidate-sample-java-1.0-SNAPSHOT.jar /candidate-sample-java-1.0-SNAPSHOT.jar


CMD ["java", "-jar", "/candidate-sample-java-1.0-SNAPSHOT.jar"]
