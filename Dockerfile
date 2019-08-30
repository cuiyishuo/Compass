FROM tomcat:8
# 定义匿名卷
VOLUME /tmp
# 复制jar包到tomcat
ADD compass-web/target/compass-web-1.0-SNAPSHOT.jar compass-web-1.0-SNAPSHOT.jar
EXPOSE 8089
# 入口点
ENTRYPOINT ["java", "-jar", "compass-web-1.0-SNAPSHOT.jar"]
