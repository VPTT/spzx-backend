package com.ptt.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ptt.spzx.manager.mapper.OrderInfoMapper;
import com.ptt.spzx.manager.mapper.OrderStatisticsMapper;
import com.ptt.spzx.manager.service.OrderInfoService;
import com.ptt.spzx.model.dto.order.OrderStatisticsDto;
import com.ptt.spzx.model.entity.order.OrderInfo;
import com.ptt.spzx.model.entity.order.OrderStatistics;
import com.ptt.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: OrderInfoServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 15:30
 * @Version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        List<OrderStatistics> statisticsList =orderStatisticsMapper.selectByDate(orderStatisticsDto);
        OrderStatisticsVo orderStatisticsVo=new OrderStatisticsVo();

        //普通方法遍历
//        List<String> dateList=new ArrayList<>();
//        List<BigDecimal> amountList=new ArrayList<>();
//        for(OrderStatistics statis: statisticsList ){
//            dateList.add(DateUtil.format(statis.getOrderDate(),"yyyy-MM-dd"));
//            amountList.add(statis.getTotalAmount());
//        }
        //流式方法遍历
        List<String> dateList=statisticsList.stream().map(
                orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(),"yyyy-MM-dd"))
                .collect(Collectors.toList());
        List<BigDecimal> amountList= statisticsList.stream()
                .map(OrderStatistics::getTotalAmount).collect(Collectors.toList());



        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);

        return orderStatisticsVo;
    }
}
