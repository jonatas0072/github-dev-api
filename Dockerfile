FROM openjdk:12-alpine

COPY target/github-1.0.0-SNAPSHOT.jar /github.jar

CMD ["java", "-jar", "/github.jar"]