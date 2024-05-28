package com.ptt.spzx.cart;

import com.ptt.spzx.common.annotation.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: CartApplication
 * Package: com.ptt.spzx.cart
 * Description:
 *  购物车数据存储在redis中  存储hash类型
 *
 * @Author ptt
 * @Create 2024/4/22 14:21
 * @Version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  // 排除数据库的自动化配置，Cart微服务不需要访问数据库
@EnableFeignClients(basePackages = {"com.ptt.spzx"})
@EnableUserLoginAuthInterceptor
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}
