package com.ptt.spzx.user.controller;

import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.user.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SmsController
 * Package: com.ptt.spzx.user.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 16:58
 * @Version 1.0
 */
@RestController
@RequestMapping("api/user/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;
    @GetMapping(value = "/sendCode/{phone}")
    public Result sendValidateCode(@PathVariable String phone){
        smsService.sendValidateCode(phone);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
