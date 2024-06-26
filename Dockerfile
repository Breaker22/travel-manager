FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY target/travel-manager-1.0.0-SNAPSHOT.jar travel-manager-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/travel-manager-1.0.0-SNAPSHOT.jar"]
EXPOSE 8080