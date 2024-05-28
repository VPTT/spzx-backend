package com.ptt.spzx.manager.controller;

import com.ptt.spzx.manager.service.SysRoleMenuService;
import com.ptt.spzx.model.dto.system.AssginMenuDto;
import com.ptt.spzx.model.vo.common.Result;
import org.apache.commons.codec.language.bm.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SysRoleMenuController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/9 15:10
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable("roleId") Long roleId)
    {
        Result result=sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return result;

    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto){
        Result result=sysRoleMenuService.doAssign(assginMenuDto);
        return result;
    }

}
