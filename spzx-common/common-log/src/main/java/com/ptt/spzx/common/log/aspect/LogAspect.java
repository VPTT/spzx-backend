package com.ptt.spzx.common.log.aspect;

import com.ptt.spzx.common.log.annotation.Log;
import com.ptt.spzx.common.log.service.AsyncOperLogService;
import com.ptt.spzx.common.log.utils.LogUtil;
import com.ptt.spzx.model.entity.system.SysOperLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: LogAspect
 * Package: com.ptt.spzx.common.log.aspect
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 17:05
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect{
    @Autowired
    private AsyncOperLogService asyncOperLogService;
//    @Around(value = "@annotation(sysLog)")
//    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog){
//        Object proceed;
//        String title = sysLog.title();
//        int type = sysLog.businessType();
//        System.out.println("title: "+title +" type "+type);
//
//
//        try {
//             proceed= joinPoint.proceed();//方法执行
//            System.out.println("业务方法后执行");
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//        return  proceed;
//    }

    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog){
        Object proceed=null;
        SysOperLog sysOperLog=new SysOperLog();
        //业务方法调用前 封装数据
        LogUtil.beforeHandleLog(sysLog,joinPoint,sysOperLog);
        try {
            proceed= joinPoint.proceed();//方法执行
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,0,null);
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.afterHandlLog(sysLog,proceed,sysOperLog,1,e.getMessage());
            throw new RuntimeException(e);

        }

        asyncOperLogService.saveSysOperLog(sysOperLog);
        return  proceed;
    }

}
