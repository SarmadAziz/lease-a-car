FROM maven:3.9.6-amazoncorretto-21 AS build

# Copy src and pom to the container
WORKDIR /app
COPY src ./src
COPY pom.xml .

# generate jar inside the container
RUN mvn clean package

FROM amazoncorretto:21-alpine AS runtime
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]

# Expose the port the app runs on
EXPOSE 8081