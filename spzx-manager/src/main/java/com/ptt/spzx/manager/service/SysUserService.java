package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.dto.system.AssginRoleDto;
import com.ptt.spzx.model.dto.system.LoginDto;
import com.ptt.spzx.model.dto.system.SysUserDto;
import com.ptt.spzx.model.entity.system.SysUser;
import com.ptt.spzx.model.vo.common.Result;

/**
 * ClassName: SysUserService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/28 17:17
 * @Version 1.0
 */
public interface SysUserService {


    Result login(LoginDto loginDto);

    Result getUserInfo(String token);

    Result logout(String token);

    Result findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    Result saveSysUser(SysUser sysUser);

    Result updateSysUser(SysUser sysUser);

    Result deleteById(Integer userId);

    Result doAssign(AssginRoleDto roleDto);
}
