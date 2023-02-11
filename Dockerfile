FROM maven:3.8.4-jdk-8-slim AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -Dmaven.test.skip=true

FROM adoptopenjdk/openjdk8:alpine-slim
RUN mkdir /opt/app
COPY --from=MAVEN_BUILD target/quotes-1.0-SNAPSHOT.jar /opt/app

ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/opt/app/quotes-1.0-SNAPSHOT.jar"]