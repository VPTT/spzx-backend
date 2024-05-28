package com.ptt.spzx.manager.service;

import com.ptt.spzx.model.vo.common.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadService
 * Package: com.ptt.spzx.manager.service
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/7 16:40
 * @Version 1.0
 */
public interface FileUploadService {
    Result fileUpload(MultipartFile file);
}
