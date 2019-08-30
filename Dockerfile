FROM tomcat:8
# 复制war包到tomcat
ADD compass-web/target/compass-web-1.0-SNAPSHOT.jar compass-web-1.0-SNAPSHOT.jar
EXPOSE 8080
