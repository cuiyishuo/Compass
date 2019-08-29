FROM tomcat:8
# 复制war包到tomcat
COPY target/*.jar /home/test/sollib/local/tomcat/webapps
