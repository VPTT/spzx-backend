package com.ptt.spzx.manager.config;

import com.ptt.spzx.manager.interceptor.LoginAuthInterceptor;
import com.ptt.spzx.manager.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfiguration
 * Package: com.ptt.spzx.manager.config
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/29 14:27
 * @Version 1.0
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private UserProperties userProperties;
    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor ;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginAuthInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns(userProperties.getNoAuthUrls());
//                //.excludePathPatterns("/admin/system/index/login" , "/admin/system/index/generateValidateCode");
//
//
//    }




    //拦截器注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
//                .excludePathPatterns("/admin/system/index/login" ,
//                        "/admin/system/index/generateValidateCode")
                .excludePathPatterns(userProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }



}
