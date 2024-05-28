package com.ptt.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysRoleUserMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/8 15:07
 * @Version 1.0
 */
@Mapper
public interface SysRoleUserMapper {

    void deleteByUserId(Long userId);

    void insert(Long userId, Long roleId);

    List<Long> findByUserId(Long userId);
}
