package com.ptt.spzx.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import com.ptt.spzx.pay.Service.AlipayService;
import com.ptt.spzx.pay.Service.PaymentInfoService;
import com.ptt.spzx.pay.mapper.PaymentInfoMapper;
import com.ptt.spzx.pay.properties.AlipayProperties;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName: AlipayController
 * Package: com.ptt.spzx.pay.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/23 17:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/api/order/alipay")
public class AlipayController {
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private AlipayProperties alipayProperties;
    @Autowired
    private PaymentInfoService paymentInfoService;

    @Operation(summary="支付宝下单")
    @GetMapping("submitAlipay/{orderNo}")
    @ResponseBody
    public Result submitAlipay(@PathVariable String orderNo){
        String form=alipayService.submitAlipay(orderNo);//返回表单页面
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }
    @RequestMapping("callback/notify")
    @ResponseBody
    public String alipayNotify(@RequestParam Map<String,String> paramMap, HttpServletRequest request){
        //调用sdk校验
        boolean signVerified=false;
        try {
            signVerified=AlipaySignature.rsaCertCheckV1(paramMap,alipayProperties.getAlipayPublicKey(),
                    AlipayProperties.charset,AlipayProperties.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        String trade_status = paramMap.get("trade_status");
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，
            //  校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            if("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)){
                paymentInfoService.updatePaymentStatus(paramMap,2);//支付宝支付-2
                return "success" ;
            }

        }
        return "failure" ;
    }
}
