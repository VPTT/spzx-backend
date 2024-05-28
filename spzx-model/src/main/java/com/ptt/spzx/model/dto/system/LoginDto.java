package com.ptt.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/*
封装登录请求参数
 */
@Data
public class LoginDto {

    @Schema(description = "用户名")
    private String userName ;
    @Schema(description = "密码")
    private String password ;
    @Schema(description = "验证码")
    private String captcha ;
    @Schema(description = "验证码key")
    private String codeKey ;

}