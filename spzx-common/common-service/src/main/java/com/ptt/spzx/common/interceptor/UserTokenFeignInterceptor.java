package com.ptt.spzx.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: UserTokenFeignInterceptor
 * Package: com.ptt.spzx.common.interceptor
 * Description:
 * openFeign远程调用时 不会传送token，就没办法根据token获取用户信息
 * 建立一个远程调用的拦截器
 * 远程调用的时候传送token
 *
 * @Author ptt
 * @Create 2024/4/22 18:56
 * @Version 1.0
 */
public class UserTokenFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        template.header("token",token);

    }
}
