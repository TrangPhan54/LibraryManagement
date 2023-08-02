FROM openjdk:11
ADD target/PersonalProject-0.0.1-SNAPSHOT.jar personalproject
ENTRYPOINT ["java", "-jar","personalproject"]
EXPOSE 8080