package com.ptt.spzx.order.controller;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.h5.OrderInfoDto;
import com.ptt.spzx.model.entity.order.OrderInfo;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.h5.TradeVo;
import com.ptt.spzx.order.service.OrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: OrderInfoController
 * Package: com.ptt.spzx.order.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 16:46
 * @Version 1.0
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping(value="/api/order/orderInfo")
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @Operation(summary = "确认下单")
    @GetMapping("auth/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "提交订单")
    @PostMapping("/auth/submitOrder")
    public Result submitOrder(@RequestBody OrderInfoDto orderInfoDto){
        Long OrderId=orderInfoService.submitOrder(orderInfoDto);
        return Result.build(OrderId,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取订单信息")
    @GetMapping("auth/{orderId}")
    public Result getOrderInfo(@PathVariable("orderId") Long orderId){
        OrderInfo orderInfo=orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "立即购买")
    @GetMapping("auth/buy/{skuId}")
    public Result buy(@PathVariable Long skuId){
        TradeVo tradeVo=orderInfoService.buy(skuId);
        return Result.build(tradeVo,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取订单分页列表")
    @GetMapping("auth/{page}/{limit}")
    public Result getOrderList(@PathVariable Integer page,@PathVariable Integer limit,
                               @RequestParam(required = false, defaultValue = "") Integer orderStatus){
        PageInfo<OrderInfo> orderInfoList=orderInfoService.getOrderList(page,limit,orderStatus);
        return Result.build(orderInfoList,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取订单信息")
    @GetMapping("auth/getOrderInfoByOrderNo/{orderNo}")
    public Result getOrderInfoByOrderNo(@PathVariable String orderNo){
        OrderInfo orderInfo=orderInfoService.getOrderInfoByOrderNo(orderNo);
        return Result.build(orderInfo,ResultCodeEnum.SUCCESS);

    }
    @Operation(summary = "获取订单分页列表")
    @GetMapping("auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    public Result updateOrderStatusPayed(@PathVariable String orderNo,@PathVariable Integer orderStatus){
        orderInfoService.updateOrderStatus(orderNo,orderStatus);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


}
