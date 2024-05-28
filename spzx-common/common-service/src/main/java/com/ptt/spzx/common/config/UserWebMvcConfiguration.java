package com.ptt.spzx.common.config;

import com.ptt.spzx.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: UserWebMvcConfiguration
 * Package: com.ptt.spzx.common.config
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/19 16:04
 * @Version 1.0
 */
public class UserWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private UserLoginAuthInterceptor userLoginAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor).addPathPatterns("/api/**");
    }
}
