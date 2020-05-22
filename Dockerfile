FROM openjdk:8
ADD target/manage_parcel-1.0-SNAPSHOT.jar manage_parcel-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/manage_parcel-1.0-SNAPSHOT.jar"]