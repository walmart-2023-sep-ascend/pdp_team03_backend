FROM openjdk:latest
EXPOSE 9200
#you can use any image from docker hub for your requirement
COPY target/pdpteam03backend-0.0.1-snapshot.jar /pdpteam03backend.jar
#copy source jar file to docker image
ENTRYPOINT ["java", "-jar", "/pdpteam03backend.jar"]
#when you run the image the jar file mentioned above will run


#FROM openjdk:latest
#EXPOSE 9010
#ADD target/team2-backend-1.jar team2-backend-1.jar
#ENTRYPOINT ["java","-jar", "/team2-backend-1.jar"]
