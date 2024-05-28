package com.ptt.spzx.order.mapper;

import com.ptt.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: OrderItemMapper
 * Package: com.ptt.spzx.order.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/23 14:33
 * @Version 1.0
 */
@Mapper
public interface OrderItemMapper {
    List<OrderItem> selectByOrderId(Long id);

    void insert(OrderItem orderItem);
}
