package com.ptt.spzx.user.service;

import com.ptt.spzx.model.dto.h5.UserLoginDto;
import com.ptt.spzx.model.dto.h5.UserRegisterDto;
import com.ptt.spzx.model.vo.h5.UserInfoVo;

/**
 * ClassName: UserInfoService
 * Package: com.ptt.spzx.user.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/19 14:10
 * @Version 1.0
 */
public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
