package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.entity.system.SysMenu;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * ClassName: SysMenuService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/8 16:32
 * @Version 1.0
 */
public interface SysMenuService {
    List<SysMenu> findNodes();

    Result save(SysMenu sysMenu);

    Result update(SysMenu sysMenu);

    Result delete(Long id);

    List<SysMenuVo> findMenuByUserId();
}
