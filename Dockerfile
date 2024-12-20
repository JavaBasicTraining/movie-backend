FROM gradle:7.5.1-jdk17-focal AS build
WORKDIR /workspace/app

# Copy gradle files first
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./

# Install dos2unix and convert line endings
RUN chmod +x gradlew && ./gradlew dependencies

# Then copy source files
COPY src ./src
RUN ./gradlew clean build

FROM eclipse-temurin:17-jre-focal
WORKDIR /workspace/app
COPY --from=build /workspace/app/build/libs/*.jar /workspace/app/movie-backend.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "movie-backend.jar"]