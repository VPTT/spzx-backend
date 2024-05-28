package com.ptt.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptt.spzx.manager.mapper.SysRoleMapper;
import com.ptt.spzx.manager.mapper.SysRoleUserMapper;
import com.ptt.spzx.manager.service.SysRoleService;
import com.ptt.spzx.model.dto.system.SysRoleDto;
import com.ptt.spzx.model.entity.system.SysRole;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/1 11:27
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public Result findMyPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        //设置分页相关参数
        PageHelper.startPage(pageNum,pageSize);

        List<SysRole> roles= sysRoleMapper.findMyPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(roles);

        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }

    @Override
    public Result saveSysRole(SysRole sysRole) {
        sysRoleMapper.save(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result updateSysRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result findAllRoles(Long userId) {
        //1。查询所有角色
        Map<String,Object> resultMap= new HashMap<>();
        List<SysRole> roles=sysRoleMapper.findAll();
        resultMap.put("allRolesList",roles);

        //2.查询当前用户已分配的角色
        List<Long> roleIds=sysRoleUserMapper.findByUserId(userId);
        resultMap.put("sysUserRoles",roleIds);

        return Result.build(resultMap,ResultCodeEnum.SUCCESS);

    }
}
