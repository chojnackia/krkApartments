FROM openjdk:11
LABEL maintainer="krk-apartments.com"
ADD target/krkApartments-0.0.1-SNAPSHOT.jar krkapartments-docker.jar
ENTRYPOINT ["java", "-jar", "krkapartments-docker.jar"]