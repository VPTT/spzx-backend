package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysMenuMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/8 16:34
 * @Version 1.0
 */
@Mapper
public interface SysMenuMapper {
    List<SysMenu> findAll();

    void insert(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    Integer countChildren(Long id);

    void delete(Long id);

    List<SysMenu> findMenuByUserId(Long id);

    SysMenu selectById(Long id);
}
