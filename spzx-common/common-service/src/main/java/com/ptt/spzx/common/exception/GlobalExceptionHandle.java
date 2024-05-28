package com.ptt.spzx.common.exception;

import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceptionHandle
 * Package: com.ptt.spzx.common.exception
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/29 11:20
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandle {
    //全局异常处理
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result error(){
//        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
//    }

    //自定义异常处理  //throw new spzxException(ResultCodeEnum )
    @ResponseBody
    @ExceptionHandler(spzxException.class)
    public Result errorSpzx(spzxException e){
        return Result.build(null, e.getResultCodeEnum());

    }

}
