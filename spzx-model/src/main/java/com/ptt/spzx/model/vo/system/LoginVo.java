package com.ptt.spzx.model.vo.system;

import lombok.Data;
/*
创建Login实体类  封装登录后的响应结果数据
 */

@Data
public class LoginVo {

    private String token ;
    private String refresh_token ;		// 该字段不会存储对应的值

}