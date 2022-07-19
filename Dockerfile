FROM adoptopenjdk/openjdk11-openj9:alpine
COPY target/expresskassa-0.0.1-SNAPSHOT.jar /expresskassa.jar
CMD ["java", "-jar", "/expresskassa.jar"]