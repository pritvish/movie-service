FROM openjdk:11
EXPOSE 8080
ADD target/movie-service.jar movie-service.jar
ENTRYPOINT ["java","-jar","movie-service.jar"]