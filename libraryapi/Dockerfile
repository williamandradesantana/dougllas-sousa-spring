# build

FROM maven:3.8.8-amazoncorretto-21-al2023 as build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

# run

FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build /build/target/*.jar ./libraryapi.jar

EXPOSE 8080
EXPOSE 9090

ENV DATASOURCE_URL_PROD=''
ENV DATASOURCE_USERNAME_PROD=''
ENV DATASOURCE_PASSWORD_PROD=''
ENV GOOGLE_AUTH_CLIENT_ID=''
ENV GOOGLE_AUTH_CLIENT_SECRET=''

ENV SPRING_PROFILES_ACTIVE='production'
ENV TZ='America/Sao_Paulo'

ENTRYPOINT ["java", "-jar", "libraryapi.jar"]