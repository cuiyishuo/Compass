FROM tomcat:8
# 复制war包到tomcat
COPY target/*.war /usr/local/tomcat/webapps/
