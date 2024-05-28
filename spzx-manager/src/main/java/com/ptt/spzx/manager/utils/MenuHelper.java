package com.ptt.spzx.manager.utils;

import com.ptt.spzx.model.entity.system.SysMenu;
import org.mockito.internal.matchers.Find;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MenuHelper
 * Package: com.ptt.spzx.manager.utils
 * Description:
 * 封装树形菜单的返回json格式
 *
 * @Author ptt
 * @Create 2024/4/8 17:04
 * @Version 1.0
 */
public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> menus){
        //
        //List<SysMenu> trees=new ArrayList<>();
        //trees= findChildren(0L,menus);
//        for(SysMenu sysMenu:menus)
//        {
//            if(sysMenu.getParentId()==0){
//                sysMenu.setChildren(findChildren(sysMenu.getId(),menus));
//                trees.add(sysMenu);
//            }
//        }
        return findChildren(0L,menus);

    }
    public static List<SysMenu> findChildren(Long parentId,List<SysMenu>menus){
        List<SysMenu> child=new ArrayList<>();
        for(SysMenu sysMenu:menus)
        {
            if(sysMenu.getParentId().equals(parentId)){
                sysMenu.setChildren(findChildren(sysMenu.getId(),menus));
                child.add(sysMenu);
            }
        }
        return child;
    }


}
