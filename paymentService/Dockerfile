FROM openjdk:11
LABEL maintainer="payment-service.com"
ADD target/paymentService-0.0.1-SNAPSHOT.jar paymentservice-docker.jar
ENTRYPOINT ["java", "-jar", "paymentservice-docker.jar"]