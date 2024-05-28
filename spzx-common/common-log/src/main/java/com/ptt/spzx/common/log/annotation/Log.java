package com.ptt.spzx.common.log.annotation;

import com.ptt.spzx.common.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: Log
 * Package: com.ptt.spzx.common.log.annotation
 * Description:
 * 创建自定义 日志记录注解
 *
 * @Author ptt
 * @Create 2024/4/15 17:00
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    public String title() ;								// 模块名称
    public OperatorType operatorType() default OperatorType.MANAGE;	// 操作人类别
    public int businessType() ;     // 业务类型（0其它 1新增 2修改 3删除）
    public boolean isSaveRequestData() default true;   // 是否保存请求的参数
    public boolean isSaveResponseData() default true;  // 是否保存响应的参数
}
