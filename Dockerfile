FROM eclipse-temurin:21-jdk

COPY target/Library-Management-System.jar /usr/app/

WORKDIR /usr/app/

EXPOSE 8085

ENTRYPOINT ["java","-jar","Library-Management-System.jar"]