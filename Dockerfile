FROM tomcat:8
# 复制war包到tomcat
COPY compass-web/target/*.jar /usr/local/tomcat/webapps/
