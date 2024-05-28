package com.ptt.spzx.order.mapper;

import com.ptt.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderLogMapper
 * Package: com.ptt.spzx.order.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/23 14:35
 * @Version 1.0
 */
@Mapper
public interface OrderLogMapper {
    void insert(OrderLog orderLog);


}
