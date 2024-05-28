package com.ptt.spzx.manager.controller;

import com.ptt.spzx.manager.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ptt.spzx.model.vo.common.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadController
 * Package: com.ptt.spzx.manager.controller
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/7 16:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestParam("file") MultipartFile file)
    {

        Result result=fileUploadService.fileUpload(file);
        return result;
    }
}
