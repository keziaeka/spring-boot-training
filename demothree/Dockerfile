# Gunakan image JDK sebagai base image
FROM openjdk:21

# Set working directory
WORKDIR /app

# Salin file JAR ke dalam container
COPY target/*.jar app.jar

# Expose port yang digunakan oleh aplikasi
EXPOSE 8080

# Jalankan aplikasi saat container dimulai
CMD ["java", "-jar", "app.jar"]

