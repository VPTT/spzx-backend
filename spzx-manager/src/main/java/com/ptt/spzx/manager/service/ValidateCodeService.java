package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.system.ValidateCodeVo;

/**
 * ClassName: ValidateCodeService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/29 15:00
 * @Version 1.0
 */
public interface ValidateCodeService {
    Result<ValidateCodeVo> generateValidateCode();
}
