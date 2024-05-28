package com.ptt.spzx.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.ptt.spzx.model.entity.user.UserInfo;
import com.ptt.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: UserLoginAuthInterceptor
 * Package: com.ptt.spzx.common.interceptor
 * Description:用户验证拦截器
 *
 * @Author ptt
 * @Create 2024/4/19 15:54
 * @Version 1.0
 */
public class UserLoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        String userJson = redisTemplate.opsForValue().get("user:spzx:" + token);
        if(userJson !=null){
            AuthContextUtil.setUserInfo(JSON.parseObject( userJson, UserInfo.class));

        }
        return true;

    }
}
