FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} book-store-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar" ,"/book-store-0.0.1-SNAPSHOT.jar"]