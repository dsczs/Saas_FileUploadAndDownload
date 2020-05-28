#!/bin/bash

############################################################################
#   author:zhanggang 370637594@qq.com
#   desc: nginx 作为 文件下载服务器
#   date: 2020年4月14日10:58:05
############################################################################


#镜像名称
docker_name="nginx_download_server"
#镜像映射的端口
port="9111"

#1.删除容器
function del_image() {
    docker rm -f $docker_name &> /dev/null
}

# 2.运行容器
function run_image() {
    docker run -d --restart=on-failure:5 \
    -p $port:80 \
    -v $PWD/download:/usr/share/nginx/html \
    --name $docker_name nginx:1.13
}

#3.修改配置文件和重启服务
function modify_conf() {
    docker cp download/default.conf $docker_name:/etc/nginx/conf.d
    docker restart $docker_name
}

del_image
run_image
modify_conf

echo "把文件放在download文件夹下 访问 http://ip:port即可"

