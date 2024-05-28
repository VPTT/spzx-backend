package com.ptt.spzx.cart.service;

import com.ptt.spzx.model.entity.h5.CartInfo;

import java.util.List;

/**
 * ClassName: CartService
 * Package: com.ptt.spzx.cart.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 14:25
 * @Version 1.0
 */
public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> getCartList();

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    void clearCart();

    List<CartInfo> getAllCkecked();

    void deleteCheked();
}
