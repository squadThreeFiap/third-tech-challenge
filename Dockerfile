FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/restaurant-finder-1.0.0.jar restaurant-finder-1.0.0.jar
EXPOSE 9090
CMD ["java", "-jar", "restaurant-finder-1.0.0.jar"]