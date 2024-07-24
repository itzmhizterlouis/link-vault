FROM openjdk:21-jdk

COPY build/libs/linkvault-0.0.1-SNAPSHOT.jar  linkvault-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "linkvault-0.0.1-SNAPSHOT.jar"]
