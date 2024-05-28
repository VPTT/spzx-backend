package com.ptt.spzx.common.log.service;

import com.ptt.spzx.model.entity.system.SysOperLog;

/**
 * ClassName: AsyncOperLogService
 * Package: com.ptt.spzx.common.log.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 17:25
 * @Version 1.0
 */

public interface AsyncOperLogService{
    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;//保存日志
}
