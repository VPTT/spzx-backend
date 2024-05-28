package com.ptt.spzx.pay;

import com.ptt.spzx.common.annotation.EnableUserLoginAuthInterceptor;
import com.ptt.spzx.pay.properties.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: PayApplication
 * Package: com.ptt.spzx.pay
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/23 17:31
 * @Version 1.0
 */
@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableFeignClients({"com.ptt.spzx.feign.order","com.ptt.spzx.feign.product"})
@EnableConfigurationProperties(AlipayProperties.class)
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }
}
