#!/bin/bash
cd `dirname $0`


img_mvn="maven:3.3.3-jdk-8"                 # docker image of maven
m2_cache=~/.m2                              # the local maven cache dir
proj_home=$PWD                              # the project root dir
img_output="uploadfiledemo"         # output image tag

#删除无效镜像
echo "======== docker rmi containers ========"
logs=$(docker images | grep none | awk '{print $3}')
for log in $logs
        do
             echo $log
	     docker rmi $log
        done
echo "删除成功"

git pull  # should use git clone https://name:pwd@xxx.git

if which mvn ; then
    echo "use local maven"
    mvn clean package -U
else
    echo "use docker maven"
    docker run --rm \
        -v $m2_cache:/root/.m2 \
        -v $proj_home:/usr/src/mymaven \
        -w /usr/src/mymaven $img_mvn mvn clean package -U
fi


sudo mv $proj_home/target/uploadfiledemo-*-SNAPSHOT.jar $proj_home/target/uploadfiledemo.jar # 兼容所有sh脚本
docker build -t $img_output .

# 如果用的 fabric8/java-jboss-openjdk8-jdk 这个镜像，必须要以下两句；默认镜像启动的容器所属用户是jboss
mkdir -p $PWD/logs
chmod -R 777 $PWD/logs

mkdir -p /home/nginx/download
chmod -R 777 /home/nginx/download

# 删除容器
docker rm -f uploadfiledemo &> /dev/null

version=`date "+%Y%m%d%H"`
# 启动镜像
docker run -d --restart=on-failure:5 --privileged \
    -w /home \
    -v $PWD/logs:/home/logs \
    -v /home/nginx/download:/home/nginx/download \
    -p 8226:8226 \
    --name uploadfiledemo uploadfiledemo \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xloggc:logs/gc_$version.log \
        -jar /home/uploadfiledemo.jar \
            --spring.profiles.active=dev

