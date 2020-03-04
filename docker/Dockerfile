FROM openjdk:8-jdk-alpine

COPY ./app/animeshop-0.0.1-SNAPSHOT.jar webapp.jar

# tell the port number the container should expose
EXPOSE 8443

# run the application
CMD ["java", "-jar", "webapp.jar"]