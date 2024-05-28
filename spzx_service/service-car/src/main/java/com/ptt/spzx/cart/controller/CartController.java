package com.ptt.spzx.cart.controller;

import com.ptt.spzx.cart.service.CartService;
import com.ptt.spzx.model.entity.h5.CartInfo;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CartController
 * Package: com.ptt.spzx.cart.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 14:24
 * @Version 1.0
 */
@RestController
@RequestMapping("api/order/cart/")
public class CartController {
    @Autowired
    private CartService cartService;

    //skuid skuid值
    //skunum 加入的sku的数量
    @Operation(summary = "添加购物车")
    @GetMapping("auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable Long skuId,@PathVariable Integer skuNum){
        cartService.addToCart(skuId,skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "查询购物车")
    @GetMapping("auth/cartList")
    public Result cartList(){
        List<CartInfo> cartInfoList=cartService.getCartList();
        return Result.build(cartInfoList,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除购物车商品")
    @DeleteMapping("auth/deleteCart/{skuId}")
    public Result deleteCart(@PathVariable Long skuId){
        cartService.deleteCart(skuId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary="更新购物车商品选中状态")
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable Long skuId,@PathVariable Integer isChecked){
        cartService.checkCart(skuId,isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary="更新购物车商品全部选中状态")
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public Result allCheckCart(@PathVariable Integer isChecked){
        cartService.allCheckCart(isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary="清空购物车")
    @GetMapping("/auth/clearCart")
    public Result clearCart(){
        cartService.clearCart();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary="选中的购物车")
    @GetMapping(value = "/auth/getAllCkecked")
    public List<CartInfo> getAllCkecked() {
        return cartService.getAllCkecked();
    }

    @GetMapping("auth/deleteCheked")
    public Result deleteCheked(){
        cartService.deleteCheked();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }





}
