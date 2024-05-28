package com.ptt.spzx.pay.Service.impl;

import com.alibaba.fastjson2.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.ptt.spzx.common.exception.spzxException;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.pay.config.AlipayConfiguration;

import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ptt.spzx.model.entity.pay.PaymentInfo;
import com.ptt.spzx.pay.Service.AlipayService;
import com.ptt.spzx.pay.Service.PaymentInfoService;
import com.ptt.spzx.pay.properties.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * ClassName: AlipayServiceImpl
 * Package: com.ptt.spzx.pay.Service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/24 10:05
 * @Version 1.0
 */
@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private PaymentInfoService paymentInfoService;
    @Autowired
    private  AlipayProperties alipayProperties;
    @Autowired
    private AlipayClient alipayClient;
    @Override
    public String submitAlipay(String orderNo) {
        //保存支付记录
        PaymentInfo paymentInfo= paymentInfoService.savePaymentInfo(orderNo);

        //调用支付宝服务接口
        AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();
        //同步回调
        alipayRequest.setReturnUrl(alipayProperties.getReturnPaymentUrl());
        //异步回调
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyPaymentUrl());
        // 准备请求参数 ，声明一个map 集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("out_trade_no",paymentInfo.getOrderNo());
        map.put("product_code","QUICK_WAP_WAY");
        //map.put("total_amount",paymentInfo.getAmount());实际支付代码
        map.put("total_amount",new BigDecimal("0.01"));//测试支付
        map.put("subject",paymentInfo.getContent());
        alipayRequest.setBizContent(JSON.toJSONString(map));

        try {
            AlipayTradeWapPayResponse payResponse = alipayClient.pageExecute(alipayRequest);
            if(payResponse.isSuccess()){
                String body = payResponse.getBody();
                return body;
            }
            else throw new spzxException(ResultCodeEnum.DATA_ERROR);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }


    }
}
