FROM openjdk:11

# Set the working directory in the container
WORKDIR /achat

# Copy the compiled JAR file from the target directory to the container
COPY ./target/achat-1.0.jar achat.jar

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "achat.jar"]