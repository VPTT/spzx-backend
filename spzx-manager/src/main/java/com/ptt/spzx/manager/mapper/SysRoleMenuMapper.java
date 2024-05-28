package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.dto.system.AssginMenuDto;
import com.ptt.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleMenuMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/9 15:12
 * @Version 1.0
 */
@Mapper
public interface SysRoleMenuMapper {
    void updateIsHalf(Long pId);

    List<Long> findSysRoleMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
