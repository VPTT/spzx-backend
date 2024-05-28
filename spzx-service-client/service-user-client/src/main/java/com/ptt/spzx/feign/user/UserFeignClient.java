package com.ptt.spzx.feign.user;

import com.ptt.spzx.model.entity.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: userFeignClient
 * Package: com.ptt.spzx.feign.user
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/23 14:38
 * @Version 1.0
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable("id") Long id);
}
