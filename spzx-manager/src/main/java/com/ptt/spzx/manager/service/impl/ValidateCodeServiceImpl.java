package com.ptt.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.ptt.spzx.manager.service.ValidateCodeService;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ValidateCodeServiceImple
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 * 实现 生成图片校验码
 *
 * @Author ptt
 * @Create 2024/3/29 15:00
 * @Version 1.0
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public Result<ValidateCodeVo> generateValidateCode() {
        //通过工具生成图片验证码 hutool
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String code = circleCaptcha.getCode();//四位验证码值
        String imageBase64 = circleCaptcha.getImageBase64();//返回图片验证码 base64是编码

        //把验证码存储到redis  key：uuid  value：验证码值
        String key= UUID.randomUUID().toString().replace("-","");
        //设置过期时间
        redisTemplate.opsForValue().set("user:validate"+key,code,5, TimeUnit.MINUTES);


        return Result.build(new ValidateCodeVo(key,"data:image/png;base64," + imageBase64), ResultCodeEnum.SUCCESS);
    }
}
