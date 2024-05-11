FROM openjdk:17

WORKDIR /app

COPY target/soat3-ms-pedidos-0.0.1-SNAPSHOT.jar /app/soat3-ms-pedidos.jar

EXPOSE 8083

ENTRYPOINT [ "java", "-jar", "soat3-ms-pedidos.jar" ]