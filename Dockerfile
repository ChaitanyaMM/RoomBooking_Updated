FROM 		tomcat:8.0.21-jre8

MAINTAINER 	Chaitanya
ADD target/RoomBooking.jar RoomBooking.jar
ENTRYPOINT ["java", "-jar", "/RoomBooking.jar"]
EXPOSE 8080
 


 


 