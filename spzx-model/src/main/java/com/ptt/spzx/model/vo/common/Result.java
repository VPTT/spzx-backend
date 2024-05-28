package com.ptt.spzx.model.vo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

/**
 * ClassName: Result
 * Package: com.ptt.spzx.model.vo.common
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/28 15:52
 * @Version 1.0
 */
@Data
@Schema(description = "相应结果实体类")
public class Result<T> {
    @Schema(description = "业务状态码")
    private Integer code;
    @Schema(description = "相应消息")
    private String message;
    @Schema(description = "业务数据")
    private T data;
    private Result(){}
    public static <T> Result<T> build(T data,Integer code,String message){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T data,ResultCodeEnum resultCodeEnum)
    {
        return build(data,resultCodeEnum.getCode(),resultCodeEnum.getMessage());

    }



}
