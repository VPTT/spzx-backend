package com.ptt.spzx.utils;

import com.ptt.spzx.model.entity.system.SysUser;
import com.ptt.spzx.model.entity.user.UserInfo;

/**
 * ClassName: AuthContextUtil
 * Package: com.ptt.spzx.utils
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/29 16:54
 * @Version 1.0
 */
public class AuthContextUtil {
    //添加前端用户信息
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;
    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }


    //创建threadlocal对象
    private static final ThreadLocal<SysUser> threadLocal=new ThreadLocal<>();
    //添加数据
    public static void set(SysUser sysUser)
    {
        threadLocal.set(sysUser);
    }
    //获取数据
    public static SysUser get()
    {
        return threadLocal.get();
    }
    //删除数据
    public static void remove(){
        threadLocal.remove();
    }

}
