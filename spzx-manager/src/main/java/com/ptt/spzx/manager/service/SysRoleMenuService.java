package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.dto.system.AssginMenuDto;
import com.ptt.spzx.model.vo.common.Result;

/**
 * ClassName: SysRoleMenuService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/9 15:11
 * @Version 1.0
 */
public interface SysRoleMenuService {
    Result findSysRoleMenuByRoleId(Long roleId);

    Result doAssign(AssginMenuDto assginMenuDto);
}
