package com.ptt.spzx.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ptt.spzx.model.entity.user.UserInfo;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ClassName: AuthGlobalFilter
 * Package: com.ptt.spzx.gateway.filter
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/19 15:26
 * @Version 1.0
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        UserInfo userInfo=getUserInfo(request);
        if(antPathMatcher.match("/api/**/auth/**",path)){//"/api/**/auth/**"
            if(null == userInfo){
                ServerHttpResponse response=exchange.getResponse();
                return out(response, ResultCodeEnum.LOGIN_AUTH);
            }

        }


        return chain.filter(exchange);
    }

    private UserInfo getUserInfo(ServerHttpRequest request) {
        String token="";
        List<String> tokenList = request.getHeaders().get("token");
        if(tokenList != null){
            token=tokenList.get(0);
        }
        if( StringUtils.hasText(token)){
            String userJson=redisTemplate.opsForValue().get("user:spzx:"+token);
            if(StringUtils.hasText(userJson)){
                return JSON.parseObject(userJson,UserInfo.class);
            }
        }
        return null;
    }

    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result result = Result.build(null, resultCodeEnum);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
