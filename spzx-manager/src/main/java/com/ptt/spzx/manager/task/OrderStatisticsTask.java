package com.ptt.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.ptt.spzx.manager.mapper.OrderInfoMapper;
import com.ptt.spzx.manager.mapper.OrderStatisticsMapper;
import com.ptt.spzx.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * ClassName: OrderStatisticsTask
 * Package: com.ptt.spzx.manager.task
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 11:21
 * @Version 1.0
 */
@Component
public class OrderStatisticsTask {
    //测试定时任务  每5秒执行
    //实现类 @Scheduled + cron表达式
    //启动类开启定时任务@EnableScheduling
//    @Scheduled(cron = "0/5 * * * * ? ")
//    public void test(){
//        System.out.println("hello"+ new Date().toString());
//    }

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    //每天凌晨两点 统计前一天的
    @Scheduled(cron = "0 0 2 * * ? ")
    public void orderTotalAmountStatistics(){
        System.out.println("hello"+ new Date().toString());
        String creatTime= DateUtil.offsetDay(new Date(),-1).toString("yyyy-MM-dd");
        OrderStatistics orderStatistics= orderInfoMapper.selectOrderStatistics(creatTime);
        if(orderStatistics != null)
            orderStatisticsMapper.insert(orderStatistics);

    }

}
