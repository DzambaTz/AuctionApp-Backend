FROM openjdk:18-jdk-alpine3.15

COPY target/AuctionApp*.jar /src/app/target.jar

WORKDIR /src/app

CMD [ "java", "-jar", "target.jar" ]

EXPOSE 8080
