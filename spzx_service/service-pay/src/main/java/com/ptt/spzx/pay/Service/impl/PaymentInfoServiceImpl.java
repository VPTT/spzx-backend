package com.ptt.spzx.pay.Service.impl;

import com.alibaba.fastjson.JSON;
import com.ptt.spzx.feign.order.OrderFeignClient;
import com.ptt.spzx.feign.product.ProductFeignClient;
import com.ptt.spzx.model.dto.product.SkuSaleDto;
import com.ptt.spzx.model.entity.order.OrderInfo;
import com.ptt.spzx.model.entity.order.OrderItem;
import com.ptt.spzx.model.entity.pay.PaymentInfo;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.pay.Service.PaymentInfoService;
import com.ptt.spzx.pay.mapper.PaymentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: PaymentInfoServiceImpl
 * Package: com.ptt.spzx.pay.Service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/24 10:11
 * @Version 1.0
 */
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
    @Autowired
    private PaymentInfoMapper paymentInfoMapper;
    @Autowired
    private OrderFeignClient orderFeignClient;
    @Autowired
    private ProductFeignClient productFeignClient;
    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        PaymentInfo paymentInfo=paymentInfoMapper.selectByOrderNo(orderNo);
        if(paymentInfo !=null){
            return paymentInfo;
        }
        //远程调用获取订单信息
        OrderInfo orderInfo=orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
        paymentInfo=new PaymentInfo();
        paymentInfo.setUserId(orderInfo.getUserId());
        paymentInfo.setPayType(orderInfo.getPayType());
        String content = "";
        for(OrderItem item : orderInfo.getOrderItemList()) {
            content += item.getSkuName() + " ";
        }
        paymentInfo.setContent(content);
        paymentInfo.setAmount(orderInfo.getTotalAmount());
        paymentInfo.setOrderNo(orderNo);
        paymentInfo.setPaymentStatus(0);
        paymentInfoMapper.insert(paymentInfo);
        return paymentInfo;
    }

    //支付完成后更新状态
    @Override
    public void updatePaymentStatus(Map<String, String> paramMap,Integer payType) {
        //根据订单编号查询支付记录
        PaymentInfo paymentInfo = paymentInfoMapper.selectByOrderNo(paramMap.get("out_trade_no"));
        //如果状态已支付  不需要更新
        if(paymentInfo.getPaymentStatus()==1)return;

        //未支付 更新状态
        paymentInfo.setPaymentStatus(1);
        paymentInfo.setOutTradeNo(paramMap.get("trade_no"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(JSON.toJSONString(paramMap));
        paymentInfoMapper.updateById(paymentInfo);

        //更新订单状态
        orderFeignClient.updateOrderStatusPayed(paymentInfo.getOrderNo(),payType);

        //更新商品销量
        OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(paymentInfo.getOrderNo()).getData();
        List<SkuSaleDto> skuSaleDtoList= orderInfo.getOrderItemList().stream().map(orderItem -> {
            SkuSaleDto saleDto = new SkuSaleDto();
            saleDto.setSkuId(orderItem.getSkuId());
            saleDto.setNum(orderItem.getSkuNum());
            return saleDto;
        }).collect(Collectors.toList());
        productFeignClient.updateSkuSaleNum(skuSaleDtoList);



    }
}
