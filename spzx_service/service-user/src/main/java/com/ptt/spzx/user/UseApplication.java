package com.ptt.spzx.user;

import com.ptt.spzx.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * ClassName: UseApplication
 * Package: com.ptt.spzx.user
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 16:54
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ptt.spzx"} )
@EnableUserLoginAuthInterceptor
public class UseApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseApplication.class,args);
    }
}
