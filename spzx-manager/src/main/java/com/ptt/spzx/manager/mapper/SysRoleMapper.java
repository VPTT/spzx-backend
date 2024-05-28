package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.dto.system.SysRoleDto;
import com.ptt.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysRoleMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/1 11:27
 * @Version 1.0
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> findMyPage(SysRoleDto sysRoleDto);

    void save(SysRole sysRole);

    void update(SysRole sysRole);

    void deleteById(Long roleId);

    List<SysRole> findAll();
}
