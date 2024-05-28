package com.ptt.spzx.manager.service.impl;

import com.ptt.spzx.common.log.service.AsyncOperLogService;
import com.ptt.spzx.manager.mapper.AsyncOperLogMapper;
import com.ptt.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: AsyncOperLogServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 17:26
 * @Version 1.0
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {
    @Autowired
    private AsyncOperLogMapper asyncOperLogMapper;
    //保存日志
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        asyncOperLogMapper.insert(sysOperLog);

    }


}
