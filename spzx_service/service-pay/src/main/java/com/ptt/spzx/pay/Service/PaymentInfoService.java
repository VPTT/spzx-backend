package com.ptt.spzx.pay.Service;

import com.ptt.spzx.model.entity.pay.PaymentInfo;

import java.util.Map;

/**
 * ClassName: PaymentInfoService
 * Package: com.ptt.spzx.pay.Service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/24 10:10
 * @Version 1.0
 */
public interface PaymentInfoService {
    //保存支付记录
    PaymentInfo savePaymentInfo(String orderNo);

    void updatePaymentStatus(Map<String, String> paramMap,Integer payType);
}
