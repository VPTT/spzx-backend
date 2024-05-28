package com.ptt.spzx.feign.cart;

import com.ptt.spzx.model.entity.h5.CartInfo;
import com.ptt.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ClassName: CartFeignClient
 * Package: com.ptt.spzx.feign.cart
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 17:10
 * @Version 1.0
 */

@FeignClient(value = "service-cart")
public interface CartFeignClient {

    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    public abstract List<CartInfo> getAllCkecked() ;
    @GetMapping("/api/order/cart/auth/deleteCheked")
    public Result deleteCheked();

}