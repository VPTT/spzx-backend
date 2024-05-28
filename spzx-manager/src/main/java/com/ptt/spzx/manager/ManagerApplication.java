package com.ptt.spzx.manager;

import com.ptt.spzx.common.log.annotation.EnableLogAspect;
import com.ptt.spzx.manager.properties.MinioProperties;
import com.ptt.spzx.manager.properties.UserProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName: ManagerApplication
 * Package: com.ptt.spzx.manager
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/28 17:13
 * @Version 1.0
 */
//@MapperScan("com.ptt.mapper")
@SpringBootApplication
@ComponentScan(basePackages = "com.ptt.spzx")
@EnableConfigurationProperties(value = {UserProperties.class, MinioProperties.class})
@EnableScheduling
@EnableLogAspect
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }


}
