package com.ptt.spzx.order.service;

import com.github.pagehelper.PageInfo;
import com.ptt.spzx.model.dto.h5.OrderInfoDto;
import com.ptt.spzx.model.entity.order.OrderInfo;
import com.ptt.spzx.model.vo.h5.TradeVo;

/**
 * ClassName: OrderInfoService
 * Package: com.ptt.spzx.order.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 17:00
 * @Version 1.0
 */
public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> getOrderList(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getOrderInfoByOrderNo(String orderNo);

    void updateOrderStatus(String orderNo, Integer orderStatus);
}
