FROM openjdk:17-jdk
WORKDIR /simulation
EXPOSE 8080
ADD /build/libs/simulation-0.0.1-SNAPSHOT.jar simulation.jar
ENTRYPOINT ["java", "-jar", "simulation.jar"]