FROM ibmjava
# 更新包管理工具
RUN apt-get update
# 安装vim
RUN apt-get -y install vim
VOLUME /tmp
COPY target/dockerfile-demo-1.0.1-SNAPSHOT.jar dockerfile-demo.jar
ENTRYPOINT ["java","-jar","/dockerfile-demo.jar"]