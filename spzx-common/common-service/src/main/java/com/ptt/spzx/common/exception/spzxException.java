package com.ptt.spzx.common.exception;

import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * ClassName: spzxException
 * Package: com.ptt.spzx.common.exception
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/29 11:23
 * @Version 1.0
 */
@Data
public class spzxException extends RuntimeException{
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;
    public spzxException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum=resultCodeEnum;
        this.code=resultCodeEnum.getCode();
        this.message=resultCodeEnum.getMessage();
    }

}
