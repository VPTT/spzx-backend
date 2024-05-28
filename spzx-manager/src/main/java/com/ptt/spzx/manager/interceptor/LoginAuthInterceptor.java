package com.ptt.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ptt.spzx.manager.properties.UserProperties;
import com.ptt.spzx.model.entity.system.SysUser;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.utils.AuthContextUtil;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Struct;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: LoginAuthInterceptor
 * Package: com.ptt.spzx.manager.interceptor
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/29 16:58
 * @Version 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor{
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求方式 post get
        //如果请求方式是options 预检请求 直接放行
        if( "OPTIONS".equals(request.getMethod())){
            return true;
        }

        //从请求头获取token
        //token为null 返回错误提示 未登录
        String token=request.getHeader("token");
        if(StrUtil.isBlankIfStr(token)){
            responseNoLoginInfo(response);
            return false;
        }

        //token不为空 查询redis中验证token是否一致
        String userInfo=redisTemplate.opsForValue().get("user:login:" + token);
        if(StrUtil.isBlankIfStr(userInfo)){
            responseNoLoginInfo(response);
            return false;
        }

        //查询一致  把用户信息放到threadlocal中
        AuthContextUtil.set(JSON.parseObject(userInfo, SysUser.class));

        //延长redis用户信息数据的过期时间
        redisTemplate.expire("user:login:" + token,30, TimeUnit.MINUTES);
        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //删除threadlocal数据
        AuthContextUtil.remove();
    }
}
