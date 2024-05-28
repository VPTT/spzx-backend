package com.ptt.spzx.order;

import com.ptt.spzx.common.annotation.EnableUserLoginAuthInterceptor;
import com.ptt.spzx.common.annotation.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: OrderApplication
 * Package: com.ptt.spzx.order
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 16:45
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients("com.ptt.spzx")
@EnableUserTokenFeignInterceptor
@EnableUserLoginAuthInterceptor
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
