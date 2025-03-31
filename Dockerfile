# Sử dụng OpenJDK 17
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép đúng tên file JAR vào container
COPY build/libs/movie_backend-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng 8080
EXPOSE 8080

# Chạy ứng dụng khi container khởi động
CMD ["java", "-jar", "app.jar"]