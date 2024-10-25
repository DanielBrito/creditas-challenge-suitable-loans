# Use Amazon Corretto image
FROM amazoncorretto:17.0.0

# Adding metadata about the project
LABEL daniel.email="daniel@email.com"

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/loan-0.0.1.jar loan.jar

#Run the JAR file
ENTRYPOINT ["java","-jar","loan.jar"]
