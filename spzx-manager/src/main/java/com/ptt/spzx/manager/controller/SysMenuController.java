package com.ptt.spzx.manager.controller;

import com.ptt.spzx.manager.service.SysMenuService;
import com.ptt.spzx.model.entity.system.SysMenu;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SysMenuController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/8 16:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    //菜单列表方法 返回Element Plus要求的json格式
    @GetMapping("/findNodes")
    public Result findNodes(){

        return Result.build(sysMenuService.findNodes(), ResultCodeEnum.SUCCESS);
    }
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu){
        Result result=sysMenuService.save(sysMenu);
        return result;

    }

    //TODO 疑问 sysMenu实体类中并没有id这个属性，但是前端返回的实体中有id这个属性 为什么后台也能接收到id属性呢
    //解答 因为实现了一个基本属性类  BaseEntity中有id属性
    @PutMapping("/updateById")
    public Result update(@RequestBody SysMenu sysMenu){
        Result result=sysMenuService.update(sysMenu);
        return result;
    }

    @DeleteMapping("/removeById/{id}")
    public Result delete(@PathVariable Long id)
    {
        Result result=sysMenuService.delete(id);
        return result;
    }



}
