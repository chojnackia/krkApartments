FROM openjdk:11
LABEL maintainer="krk-apartmentEntities.com"
ADD target/krkApartments-0.0.1-SNAPSHOT.jar krkapartments-docker.jar
#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "krkapartments-docker.jar"]