package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderInfoMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 14:12
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectOrderStatistics(String creatTime);
}
