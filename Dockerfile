FROM tomcat:8
# 复制war包到tomcat
ADD compass-web/target/*.jar /usr/local/tomcat/webapps/compass-web-1.0-SNAPSHOT.jar
EXPOSE 8085
