FROM adoptopenjdk/openjdk11

MAINTAINER Alberico Colaco

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]