package com.ptt.spzx.common.log.annotation;

import com.ptt.spzx.common.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableLogAspect
 * Package: com.ptt.spzx.common.log.annotation
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 17:12
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = LogAspect.class) //通过Import注解导入日志切面类到Spring容器中
public @interface EnableLogAspect {
}
