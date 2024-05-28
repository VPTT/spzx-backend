package com.ptt.spzx.manager.service.impl;

import com.ptt.spzx.common.exception.spzxException;
import com.ptt.spzx.manager.mapper.SysMenuMapper;
import com.ptt.spzx.manager.mapper.SysRoleMenuMapper;
import com.ptt.spzx.manager.service.SysMenuService;
import com.ptt.spzx.manager.utils.MenuHelper;
import com.ptt.spzx.model.entity.system.SysMenu;
import com.ptt.spzx.model.entity.system.SysUser;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.system.SysMenuVo;
import com.ptt.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SysMenuServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/8 16:33
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> menus= sysMenuMapper.findAll();
        if(CollectionUtils.isEmpty(menus)){
            return null;
        }
        return MenuHelper.buildTree(menus);


    }

    @Override
    public Result save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        //新添加子菜单之后 需要将父菜单 isHalf=1
        updateSysRoleMenu(sysMenu);

        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    private void updateSysRoleMenu(SysMenu sysMenu){
        SysMenu pMenu=sysMenuMapper.selectById(sysMenu.getParentId());
        if(pMenu!=null) {
            sysRoleMenuMapper.updateIsHalf(pMenu.getId());
            updateSysRoleMenu(pMenu);
        }
    }


    @Override
    public Result update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result delete(Long id) {
        if ( sysMenuMapper.countChildren(id) > 0){ //有子菜单  不允许删除
            throw new spzxException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.delete(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Override
    public List<SysMenuVo> findMenuByUserId() {

        SysUser sysUser = AuthContextUtil.get();
        Long id=sysUser.getId();
        List<SysMenu> sysMenuList = sysMenuMapper.findMenuByUserId(id);

        List<SysMenu> sysMenuTreeList=MenuHelper.buildTree(sysMenuList);

        //SysMenu->SysMenuVo
        return this.buildMenus(sysMenuTreeList);



    }
    private List<SysMenuVo> buildMenus(List<SysMenu> sysMenuTreeList) {
        List<SysMenuVo> sysMenuVoList=new ArrayList<>();
        for (SysMenu menu : sysMenuTreeList) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(menu.getTitle());
            sysMenuVo.setName(menu.getComponent());
            List<SysMenu> children = menu.getChildren();
            if(! CollectionUtils.isEmpty(children) ){
                sysMenuVo.setChildren( buildMenus(children) );
            }

            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }


}
