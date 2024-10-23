WORKDIR /app
FROM amazoncorretto:17.0.0
LABEL daniel.com="daniel@email.com"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} loan.jar
ENTRYPOINT ["java","-jar","/loan.jar"]
