FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY build/libs/carshowroom-0.1.jar /carshowroom.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/carshowroom.jar"]