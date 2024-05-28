package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.dto.system.SysRoleDto;
import com.ptt.spzx.model.entity.system.SysRole;
import com.ptt.spzx.model.vo.common.Result;

/**
 * ClassName: SysRoleService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/1 11:27
 * @Version 1.0
 */
public interface SysRoleService {
    Result findMyPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    Result saveSysRole(SysRole sysRole);

    Result updateSysRole(SysRole sysRole);

    Result deleteById(Long roleId);

    Result findAllRoles(Long userId);
}
