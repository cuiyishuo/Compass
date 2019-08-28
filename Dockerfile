FROM tomcat:8
# 复制war包到tomcat
COPY target/*.jar /usr/local/tomcat/webapps/
