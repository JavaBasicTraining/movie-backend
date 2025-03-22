FROM gradle:7.5.1-jdk17-focal AS build
WORKDIR /workspace/app
COPY . /workspace/app
RUN chmod +x ./gradlew # if error in this, open git bash for window and run dos2unix gradlew to change format to unix
RUN ./gradlew clean build

FROM openjdk:17-jdk
WORKDIR /workspace/app
COPY --from=build /workspace/app/build/libs/*.jar /workspace/app/app.jar
EXPOSE 8081

CMD ["java","-jar", "app.jar"]