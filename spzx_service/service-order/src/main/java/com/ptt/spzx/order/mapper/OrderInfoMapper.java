package com.ptt.spzx.order.mapper;

import com.ptt.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: OrderInfoMapper
 * Package: com.ptt.spzx.order.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/22 17:00
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {
    void insert(OrderInfo orderInfo);

    OrderInfo selectById(Long orderId);

    List<OrderInfo> selectByStatus(Long userId, Integer orderStatus);

    OrderInfo selectByOrderNo(String orderNo);

    void updateById(OrderInfo orderInfo);
}
