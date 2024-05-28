package com.ptt.spzx.manager.mapper;

import com.ptt.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: AsyncOperLogMapper
 * Package: com.ptt.spzx.manager.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/15 17:27
 * @Version 1.0
 */
@Mapper
public interface AsyncOperLogMapper {
    void insert(SysOperLog sysOperLog);
}
