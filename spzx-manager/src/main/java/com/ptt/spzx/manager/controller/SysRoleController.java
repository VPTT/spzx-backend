package com.ptt.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.common.log.annotation.Log;
import com.ptt.spzx.manager.service.SysRoleService;
import com.ptt.spzx.model.dto.system.SysRoleDto;
import com.ptt.spzx.model.entity.system.SysRole;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService ;


    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findMyPage(@PathVariable(name = "pageNum") Integer pageNum,
                             @PathVariable(name = "pageSize") Integer pageSize,
                             @RequestBody SysRoleDto sysRoleDto){
        Result result=sysRoleService.findMyPage(sysRoleDto, pageNum, pageSize);
        return result;

    }


    @Log(title = "角色管理：添加",businessType = 1)
    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole (@RequestBody SysRole sysRole)
    {
        Result result=sysRoleService.saveSysRole(sysRole);
        return result;

    }
    @PutMapping(value = "/updateSysRole")
    public Result updateSysRole (@RequestBody SysRole sysRole)
    {
        Result result=sysRoleService.updateSysRole(sysRole);
        return result;
    }
    @DeleteMapping(value = "/deleteById/{roleId}")
    public Result deleteById(@PathVariable Long roleId)
    {
        Result result=sysRoleService.deleteById(roleId);
        return result;
    }

    //查询所有角色
    @GetMapping("/findAllRoles/{userId}")
    public Result findAllRoles (@PathVariable Long userId)
    {
        Result result=sysRoleService.findAllRoles(userId);
        return result;
    }





    
}