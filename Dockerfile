# Use an official Tomcat image
FROM tomcat:9.0-jdk11

# Copy the WAR file to the webapps directory of Tomcat
COPY target/ROOT.war /usr/local/tomcat/webapps/

# Expose the port Tomcat is running on
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
