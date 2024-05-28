package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.dto.system.SysUserDto;
import com.ptt.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysUserMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/28 17:28
 * @Version 1.0
 */

@Mapper
public interface SysUserMapper {
    SysUser selectByUserName(String userName);

    List<SysUser> selectAll(SysUserDto sysUserDto);

    void insert(SysUser sysUser);

    void update(SysUser sysUser);

    void deleteById(Integer userId);
}
