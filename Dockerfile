FROM openjdk:8
ADD target/manage_parcel-1.0.jar manage_parcel-1.0.jar
ENTRYPOINT ["java", "-jar", "/manage_parcel-1.0.jar"]