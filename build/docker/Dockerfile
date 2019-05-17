#FROM openjdk:8
##RUN bash -c 'chmod 777 build/libs/carshowroom-server-0.0.1.jar'
#ADD build/libs/carshowroom-1.jar app.jar
##RUN bash -c 'touch /var/opt/app.jar'
#EXPOSE 9000
#ENTRYPOINT ["java","-jar","app.jar"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]