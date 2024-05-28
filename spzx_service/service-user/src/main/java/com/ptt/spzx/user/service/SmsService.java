package com.ptt.spzx.user.service;

/**
 * ClassName: SmsService
 * Package: com.ptt.spzx.user.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/17 16:59
 * @Version 1.0
 */
public interface SmsService {
    void sendValidateCode(String phone);
}
