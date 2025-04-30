FROM amazoncorretto:17-al2022-jdk as build-env
WORKDIR /app
COPY . /app
COPY ./build.sh /app/build.sh
RUN yum update -y && yum install -y findutils
RUN ./build.sh
FROM amazoncorretto:17
WORKDIR /app
COPY --from=build-env /app/build/ledger.jar /app
COPY ./src/main/resources/transactions.csv /app
CMD ["java", "-jar", "ledger.jar"]



