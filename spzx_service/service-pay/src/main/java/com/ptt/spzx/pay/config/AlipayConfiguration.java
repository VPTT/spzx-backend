package com.ptt.spzx.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.ptt.spzx.pay.properties.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: AlipayConfiguration
 * Package: com.ptt.spzx.pay.config
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/24 10:42
 * @Version 1.0
 */
@Configuration
public class AlipayConfiguration {
    @Autowired
    private AlipayProperties alipayProperties;
    @Bean
    public AlipayClient alipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(alipayProperties.getAlipayUrl() ,
                alipayProperties.getAppId() ,
                alipayProperties.getAppPrivateKey() ,
                AlipayProperties.format ,
                AlipayProperties.charset ,
                alipayProperties.getAlipayPublicKey() ,
                AlipayProperties.sign_type );
        return alipayClient;
    }
}
