package com.ptt.spzx.common.annotation;

import com.ptt.spzx.common.interceptor.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserTokenFeignInterceptor
 * Package: com.ptt.spzx.common.annotation
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 19:02
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(UserTokenFeignInterceptor.class)
public @interface EnableUserTokenFeignInterceptor {
}
