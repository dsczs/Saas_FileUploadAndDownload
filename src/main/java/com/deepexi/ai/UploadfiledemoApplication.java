package com.deepexi.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * TODO 如果需要使用数据库的话 需要去掉 exclude里面的内容
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class UploadfiledemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadfiledemoApplication.class, args);
    }

}
