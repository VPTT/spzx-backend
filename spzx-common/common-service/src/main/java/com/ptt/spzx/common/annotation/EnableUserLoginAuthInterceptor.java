package com.ptt.spzx.common.annotation;

import com.ptt.spzx.common.config.UserWebMvcConfiguration;
import com.ptt.spzx.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserLoginAuthInterceptor
 * Package: com.ptt.spzx.common.annotation
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/19 16:06
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = {UserLoginAuthInterceptor.class, UserWebMvcConfiguration.class})
public @interface EnableUserLoginAuthInterceptor {
}
