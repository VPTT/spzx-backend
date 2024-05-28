package com.ptt.spzx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * ClassName: ProductApplication
 * Package: com.ptt.spzx.product
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/16 11:29
 * @Version 1.0
 */
@SpringBootApplication
@EnableCaching
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}
