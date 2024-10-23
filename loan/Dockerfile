FROM amazoncorretto:17.0.0
LABEL daniel.com="daniel@email.com"
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} loan.jar
ENTRYPOINT ["java","-jar","/app/loan.jar"]
