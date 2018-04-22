FROM openjdk:8-jre-alpine
COPY ./target/backend-1.0-SNAPSHOT.jar /usr/src/firstproject/
WORKDIR /usr/src/firstproject
EXPOSE 8080
CMD ["java", "-jar", "backend-1.0-SNAPSHOT.jar"]