<<<<<<< HEAD
FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8
VOLUME /tmp
ADD /compass-web/target/compass-web-1.0-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8089
=======
FROM tomcat:8
# 复制war包到tomcat
COPY target/*.war /usr/local/tomcat/webapps/
>>>>>>> solfeature
