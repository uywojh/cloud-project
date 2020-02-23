FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD simons-cloud-zuul.jar simons-cloud-zuul.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/simons-cloud-zuul.jar"]
