FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon

EXPOSE 8080

CMD ["java", "-jar", "build/libs/WebTech101-0.0.1-SNAPSHOT.jar"]


