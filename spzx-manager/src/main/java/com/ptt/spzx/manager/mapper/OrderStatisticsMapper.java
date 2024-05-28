package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.dto.order.OrderStatisticsDto;
import com.ptt.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: OrderStatisticsMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 14:11
 * @Version 1.0
 */
@Mapper
public interface OrderStatisticsMapper {
    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectByDate(OrderStatisticsDto orderStatisticsDto);
}
