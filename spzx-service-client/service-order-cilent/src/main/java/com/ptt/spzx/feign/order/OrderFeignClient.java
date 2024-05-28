package com.ptt.spzx.feign.order;

import com.ptt.spzx.model.entity.order.OrderInfo;
import com.ptt.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: OrderFeignClient
 * Package: com.ptt.spzx.feign.order
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/24 10:20
 * @Version 1.0
 */
@FeignClient("service-order")
public interface OrderFeignClient {
    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo) ;

    @GetMapping("/api/order/orderInfo/auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    public Result updateOrderStatusPayed(@PathVariable String orderNo,@PathVariable Integer orderStatus);
}
