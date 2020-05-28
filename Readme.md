#### 介绍
    springboot 实现文件的上传和下载功能
    
### 使用步骤
    1. 上传nginx_server_install目录下的内容到 /home/nginx目录，执行 3.install_nginx_file_download.sh 脚本
    2. 进行项目目录 修改application.properties 中配置 file.download.path 和 执行 sh startup_dev.sh 脚本 
    3. 通过postman测试 
        post http://ip地址:8226/uploadfiledemo/test/upload
             file = 压缩文件