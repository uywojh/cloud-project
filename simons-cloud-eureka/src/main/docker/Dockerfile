FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD simons-cloud-eureka.jar simons-cloud-eureka.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/simons-cloud-eureka.jar"]
