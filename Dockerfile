### Build API
FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine AS build

EXPOSE 8080 8080

WORKDIR /build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod -R +x mvnw
RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw package -DskipTests

### Run API
FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine

LABEL maintainer="Luizalabs"

RUN mkdir /usr/share/apicomunicacao
COPY --from=build /build/target/apicomunicacao-*.jar /usr/share/apicomunicacao/apicomunicacao.jar

ENTRYPOINT ["java", "-Xms2G", "-Xmx4G", "-jar", "/usr/share/apicomunicacao/apicomunicacao.jar"]