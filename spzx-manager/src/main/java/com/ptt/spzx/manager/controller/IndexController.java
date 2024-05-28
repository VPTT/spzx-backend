package com.ptt.spzx.manager.controller;
import com.ptt.spzx.manager.service.SysMenuService;
import com.ptt.spzx.manager.service.SysUserService;
import com.ptt.spzx.manager.service.ValidateCodeService;
import com.ptt.spzx.model.dto.system.LoginDto;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.system.SysMenuVo;
import com.ptt.spzx.model.vo.system.ValidateCodeVo;
import com.ptt.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: IndexController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/28 17:14
 * @Version 1.0
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;


    //用户登录
    @Operation(summary = "登录方法login")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto)
    {
        Result result=userService.login(loginDto);
        return result;


    }

    @GetMapping(value = "generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        Result<ValidateCodeVo> result=validateCodeService.generateValidateCode();
        return result;

    }

//    @GetMapping("getUserInfo")
//    public Result getUserInfo(@RequestHeader(value = "token") String token){
//
//        Result result=userService.getUserInfo(token);
//        return result;
//    }
    @GetMapping("getUserInfo")
    public Result getUserInfo() {//直接从threadlocal中获取用户信息
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);

    }

    //用户退出
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token)
    {
        //删除redis中的指定token
        Result result=userService.logout(token);
        return result;

    }

    //查询用户可以操作的菜单
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> lists=sysMenuService.findMenuByUserId();
        return Result.build(lists,ResultCodeEnum.SUCCESS);

    }


}
