package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.dto.order.OrderStatisticsDto;
import com.ptt.spzx.model.vo.order.OrderStatisticsVo;

/**
 * ClassName: OrderInfoService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 15:30
 * @Version 1.0
 */
public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
