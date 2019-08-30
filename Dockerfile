FROM tomcat:8
# 复制war包到tomcat
ADD compass-web/target/*.jar /sollib/local/tomcat/webapps/compass-web-1.0-SNAPSHOT.jar
EXPOSE 8080
