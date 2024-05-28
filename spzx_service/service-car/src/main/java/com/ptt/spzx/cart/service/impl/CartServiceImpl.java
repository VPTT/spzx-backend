package com.ptt.spzx.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.ptt.spzx.cart.service.CartService;
import com.ptt.spzx.feign.product.ProductFeignClient;
import com.ptt.spzx.model.entity.h5.CartInfo;
import com.ptt.spzx.model.entity.product.ProductSku;
import com.ptt.spzx.model.entity.user.UserInfo;
import com.ptt.spzx.utils.AuthContextUtil;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: CartServiceImpl
 * Package: com.ptt.spzx.cart.service.impl
 * Description:
 *   redis存储购物车数据
 *      key 用户id
 *      field skuId
 *      value sku信息
 *
 * @Author ptt
 * @Create 2024/4/22 14:26
 * @Version 1.0
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private ProductFeignClient productFeignClient;
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //1.必须登录状态  获取用户id  作为redis中的hash的key值
        //从threadlocal中获取用户id
        UserInfo userInfo=AuthContextUtil.getUserInfo();
        Long userId = userInfo.getId();
        String cartKey=this.getCarKey(userId);

        // 2.从redis中获取购物车数据  根据用户id + skuid（hash类型key+field）
        Object cartInfoObj=redisTemplate.opsForHash().get(cartKey,skuId.toString());

        //3.如果购物车存在添加商品 商品数量增加
        CartInfo cartInfo=new CartInfo();
        if(cartInfoObj!=null) {
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);//购物车选中属性
            cartInfo.setUpdateTime(new Date());

        }
        //4.如果购物车没有该商品，直接添加商品到购物车
        //远程调用实现 nacos+openFeign实现 根据skuId获取商品sku信息
        else{
            //TODO 远程调用  根据skuid获取商品sku信息
            ProductSku productSku = productFeignClient.getBySkuId(skuId) ;
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }
        redisTemplate.opsForHash().put(cartKey,skuId.toString(),JSON.toJSONString(cartInfo));
    }

    @Override
    public List<CartInfo> getCartList() {
        Long id = AuthContextUtil.getUserInfo().getId();
        String cartKey=this.getCarKey(id);
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        //List<Object> -> List<CartInfo>
        if(!CollectionUtils.isEmpty(objectList)){
            List<CartInfo> cartInfoList=objectList.stream().map(cartItem ->
                    JSON.parseObject(cartItem.toString(),CartInfo.class))
                    .sorted( (o1,o2) -> o2.getUpdateTime().compareTo(o1.getUpdateTime()))
                    .collect(Collectors.toList());
            return cartInfoList;
        }
        return new ArrayList<>();//不要返回null 要返回一个空集合[]
    }

    @Override
    public void deleteCart(Long skuId) {
        Long id = AuthContextUtil.getUserInfo().getId();
        String carKey = this.getCarKey(id);
        redisTemplate.opsForHash().delete(carKey, skuId.toString());

    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        //更新购物车状态
        Long id = AuthContextUtil.getUserInfo().getId();
        String carKey = this.getCarKey(id);
        if(redisTemplate.opsForHash().hasKey(carKey,String.valueOf(skuId))) {
            CartInfo cartInfo = JSON.parseObject(String.valueOf(redisTemplate.opsForHash()
                    .get(carKey, String.valueOf(skuId))), CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            cartInfo.setUpdateTime(new Date());
            redisTemplate.opsForHash().put(carKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
        }
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        Long id = AuthContextUtil.getUserInfo().getId();
        String carKey = this.getCarKey(id);

        List<Object> objectList = redisTemplate.opsForHash().values(carKey);
        if(!CollectionUtils.isEmpty(objectList)){
            objectList.stream().map(objItem -> {
                CartInfo cartInfo=JSON.parseObject(objItem.toString(),CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                cartInfo.setUpdateTime(new Date());
                return cartInfo;
            }).forEach(cartInfo ->
                    redisTemplate.opsForHash().put(carKey,cartInfo.getSkuId().toString(),JSON.toJSONString(cartInfo)));

        }
    }

    @Override
    public void clearCart() {
        Long id = AuthContextUtil.getUserInfo().getId();
        String carKey = this.getCarKey(id);
        redisTemplate.delete(carKey);
    }

    @Override
    public List<CartInfo> getAllCkecked() { //返回所有选中的商品sku  状态=1
        Long id = AuthContextUtil.getUserInfo().getId();
        String cartKey=this.getCarKey(id);
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        //List<Object> -> List<CartInfo>
        if(!CollectionUtils.isEmpty(objectList)){
            List<CartInfo> cartInfoList=objectList.stream().map(cartItem ->
                            JSON.parseObject(cartItem.toString(),CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked()==1)
                    .collect(Collectors.toList());
            return cartInfoList;
        }
        return new ArrayList<>();//不要返回null 要返回一个空集合[]

    }

    @Override
    public void deleteCheked() {
        Long id = AuthContextUtil.getUserInfo().getId();
        String carKey=getCarKey(id);
        List<Object> cartList = redisTemplate.opsForHash().values(carKey);
        if( !CollectionUtils.isEmpty(cartList)){
            cartList.stream().map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(),CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked()==1)
                    .forEach(cartChecked -> redisTemplate.opsForHash().delete(carKey,
                            String.valueOf(cartChecked.getSkuId())));
        }


    }

    private String getCarKey(Long userId)
    {
        return "user:cart"+userId;
    }
}
