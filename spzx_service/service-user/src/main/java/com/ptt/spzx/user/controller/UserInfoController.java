package com.ptt.spzx.user.controller;

import com.ptt.spzx.model.dto.h5.UserLoginDto;
import com.ptt.spzx.model.dto.h5.UserRegisterDto;
import com.ptt.spzx.model.entity.user.UserInfo;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.h5.UserInfoVo;
import com.ptt.spzx.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserInfoController
 * Package: com.ptt.spzx.user.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/19 14:09
 * @Version 1.0
 */
@Tag(name = "会员用户接口")
@RestController
@RequestMapping("api/user/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "会员注册")
    @PostMapping("register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto){
        userInfoService.register(userRegisterDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);

    }
    @Operation(summary = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return Result.build(userInfoService.login(userLoginDto), ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("auth/getCurrentUserInfo")
    public Result getCurrentUserInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        UserInfoVo userInfo=userInfoService.getCurrentUserInfo(token);
        return Result.build(userInfo,ResultCodeEnum.SUCCESS);


    }

}
