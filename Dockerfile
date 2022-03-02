FROM tomcat:9-jdk8-openjdk

ADD /webapp /usr/local/tomcat/webapps/app
ADD /webapp/WEB-INF/lib/mysql-connector.jar /usr/local/tomcat/lib/

CMD [ "catalina.sh", "run" ]