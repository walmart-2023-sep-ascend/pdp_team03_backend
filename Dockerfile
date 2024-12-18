FROM openjdk:latest
EXPOSE 9200
#you can use any image from docker hub for your requirement
COPY target/pdpteam03backend-0.0.1-snapshot.jar /pdpteam03backenddocker.jar
#copy source jar file to docker image
ENTRYPOINT ["java", "-jar", "/pdpteam03backenddocker.jar"]
#when you run the image the jar file mentioned above will run

# appended for testing from naveen 

#FROM openjdk:latest
#EXPOSE 9010
#ADD target/team2-backend-1.jar team2-backend-1.jar
#ENTRYPOINT ["java","-jar", "/team2-backend-1.jar"]
