FROM tomcat:9-jre8

RUN apt-get update && apt-get install -y libreoffice unoconv
RUN rm -rf /usr/local/tomcat/webapps/*
ADD procedurescheduling-1.0.0.war /usr/local/tomcat/webapps/ROOT.war
COPY server.xml /usr/local/tomcat/conf/

EXPOSE 5051