#!/bin/bash

############################################################################
#   author:zhanggang 370637594@qq.com
#   desc: nginx 作为 server
#   date: 2020年4月14日10:58:05
############################################################################

#镜像名称
docker_name="nginx_server"
#镜像映射的端口
port="80"

#1.删除容器
function del_image() {
    docker rm -f $docker_name &> /dev/null
}

# 2.运行容器
function run_image() {
    docker run -d --restart=on-failure:5 \
    -p $port:80 \
    --name $docker_name nginx:1.13
}

del_image
run_image