FROM tomcat:9-jdk8-openjdk

ADD . /usr/local/tomcat/webapps/app

CMD [ "catalina.sh", "run" ]