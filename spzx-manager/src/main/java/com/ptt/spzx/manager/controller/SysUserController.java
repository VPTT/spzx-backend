package com.ptt.spzx.manager.controller;

import com.ptt.spzx.manager.service.SysUserService;
import com.ptt.spzx.model.dto.system.AssginRoleDto;
import com.ptt.spzx.model.dto.system.SysUserDto;
import com.ptt.spzx.model.entity.system.SysUser;
import com.ptt.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SysUserController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/3 14:52
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService service;

    //用户条件分页查询接口
    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize,
                             SysUserDto sysUserDto)
    {
        Result result=service.findByPage(sysUserDto,pageNum,pageSize);
        return result;
    }

    //用户添加
    @PostMapping("/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser)
    {
        Result result=service.saveSysUser(sysUser);
        return result;
    }

    //用户修改
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser)
    {
        Result result=service.updateSysUser(sysUser);
        return result;
    }

    //用户删除
    @DeleteMapping("/deleteById/{userId}")
    public Result deleteById(@PathVariable Integer userId)
    {
        Result result=service.deleteById(userId);
        return result;
    }
    //用户分配角色 保存分配数据
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleDto roleDto)
    {
        Result result=service.doAssign(roleDto);
        return result;

    }


}
