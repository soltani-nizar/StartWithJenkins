FROM tomcat:8.5-jre8

WORKDIR /usr/local/tomcat

RUN rm -rf /usr/local/tomcat/webapps/ROOT

ADD crm-application.war ./webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
