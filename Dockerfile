FROM openjdk:11
ENV APP_HOME=C:\workspace\product-microservice
WORKDIR $APP_HOME
COPY build/libs/*.jar app-product.jar
CMD ["java", "-jar", "app-product.jar"]