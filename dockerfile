FROM openjdk:8-jre-alpine
#USER app
WORKDIR /app
COPY target/mockifyHoster-1.0-SNAPSHOT.jar /app/
copy ProjectsData /app/ProjectsData/
EXPOSE 80 8080
CMD "cd /app/"
CMD ["java","-jar","mockifyHoster-1.0-SNAPSHOT.jar"]