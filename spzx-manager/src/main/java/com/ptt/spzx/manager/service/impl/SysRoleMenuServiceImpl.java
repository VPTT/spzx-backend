package com.ptt.spzx.manager.service.impl;

import com.ptt.spzx.manager.mapper.SysMenuMapper;
import com.ptt.spzx.manager.mapper.SysRoleMenuMapper;
import com.ptt.spzx.manager.service.SysMenuService;
import com.ptt.spzx.manager.service.SysRoleMenuService;
import com.ptt.spzx.model.dto.system.AssginMenuDto;
import com.ptt.spzx.model.entity.system.SysMenu;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleMenuServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/9 15:11
 * @Version 1.0
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Result findSysRoleMenuByRoleId(Long roleId) {
        Map<String,Object> resultMap=new HashMap<>();
        List<SysMenu> sysMenuList = sysMenuService.findNodes();
        resultMap.put("sysMenuList", sysMenuList);
        resultMap.put("roleMenuIds",sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId));
        return Result.build(resultMap, ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result doAssign(AssginMenuDto assginMenuDto) {
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        List<Map<String, Number>> menuIdList = assginMenuDto.getMenuIdList();
        if(menuIdList !=null && menuIdList.size()>0)//用户分配了菜单
        {
            sysRoleMenuMapper.doAssign(assginMenuDto);

        }

//        for(Map<String , Number> menu:assginMenuDto.getMenuIdList()){
//            sysRoleMenuMapper.insert(assginMenuDto.getRoleId(),menu.get("id"),menu.get("isHalf"));
//            //子菜单全选中则 isHalf为0 否则半选中为1
//        }
        return Result.build(null,ResultCodeEnum.SUCCESS);

    }
}
