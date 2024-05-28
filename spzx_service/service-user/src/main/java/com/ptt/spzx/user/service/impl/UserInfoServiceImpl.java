package com.ptt.spzx.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.ptt.spzx.common.exception.spzxException;
import com.ptt.spzx.model.dto.h5.UserLoginDto;
import com.ptt.spzx.model.dto.h5.UserRegisterDto;
import com.ptt.spzx.model.entity.user.UserInfo;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.h5.UserInfoVo;
import com.ptt.spzx.user.mapper.UserInfoMapper;
import com.ptt.spzx.user.service.UserInfoService;
import com.ptt.spzx.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserInfoServiceImpl
 * Package: com.ptt.spzx.user.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/19 14:11
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        String nickName = userRegisterDto.getNickName();
        String username = userRegisterDto.getUsername();//此处用户名填写的是手机号
        String code = userRegisterDto.getCode();
        String password = userRegisterDto.getPassword();

        //校验参数
        if(!StringUtils.hasText(nickName) || !StringUtils.hasText(username)||
        !StringUtils.hasText(code) || !StringUtils.hasText(password) ){
            throw new spzxException(ResultCodeEnum.DATA_ERROR);
        }

        //校验验证码
        if( !code.equals( redisTemplate.opsForValue().get("phone:code:"+username) ) ){
            throw new spzxException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //校验用户名不能重复  （不重复注册）
        UserInfo userInfo=userInfoMapper.selectByUserName(username);
        if(userInfo!=null){
            throw new spzxException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        userInfo=new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

        userInfoMapper.insert(userInfo);

        //redis中删除验证码信息
        //redisTemplate.delete("phone:code:"+username);



    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        //校验参数
        if( !StringUtils.hasText(username) || !StringUtils.hasText(password)){
            throw new spzxException(ResultCodeEnum.DATA_ERROR);
        }
        //校验用户是否注册
        UserInfo userInfo=userInfoMapper.selectByUserName(username);
        if(userInfo==null){
            throw new spzxException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        if( ! DigestUtils.md5DigestAsHex(password.getBytes()).equals(userInfo.getPassword()) ){
            throw new spzxException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new spzxException(ResultCodeEnum.ACCOUNT_STOP);
        }

        String token= UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set("user:spzx:"+token, JSON.toJSONString( userInfo),30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
//        String userJson = redisTemplate.opsForValue().get("user:spzx:" + token);
//        if( !StringUtils.hasText(userJson)){
//            throw new spzxException(ResultCodeEnum.LOGIN_AUTH) ;
//        }
//        UserInfo userInfo= JSON.parseObject(userJson,UserInfo.class);

        UserInfo userInfo=AuthContextUtil.getUserInfo();

        UserInfoVo userInfoVo=new UserInfoVo();
        BeanUtils.copyProperties(userInfo,userInfoVo);
        return userInfoVo;
    }
}
