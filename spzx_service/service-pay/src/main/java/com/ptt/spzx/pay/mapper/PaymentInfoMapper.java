package com.ptt.spzx.pay.mapper;

import com.ptt.spzx.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * ClassName: PaymentInfoMapper
 * Package: com.ptt.spzx.pay.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/24 10:12
 * @Version 1.0
 */
@Mapper
public interface PaymentInfoMapper {
    PaymentInfo selectByOrderNo(String orderNo);

    void insert(PaymentInfo paymentInfo);

    void updateById(PaymentInfo paymentInfo);
}
