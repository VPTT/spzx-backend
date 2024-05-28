package com.ptt.spzx.manager.controller;

import com.ptt.spzx.manager.mapper.OrderInfoMapper;
import com.ptt.spzx.manager.service.OrderInfoService;
import com.ptt.spzx.model.dto.order.OrderStatisticsDto;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: OrderInfoController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 15:29
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/admin/order/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    @GetMapping("/getOrderStatisticsData")
    public Result getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto)
    {
        OrderStatisticsVo orderStatisticsVo=orderInfoService.getOrderStatisticsData(orderStatisticsDto);
        return Result.build(orderStatisticsVo, ResultCodeEnum.SUCCESS);
    }
}
