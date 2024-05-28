package com.ptt.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ptt.spzx.common.exception.spzxException;
import com.ptt.spzx.manager.properties.MinioProperties;
import com.ptt.spzx.manager.service.FileUploadService;
import com.ptt.spzx.model.vo.common.Result;
import com.ptt.spzx.model.vo.common.ResultCodeEnum;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName: FileUploadServiceImpl
 * Package: com.ptt.spzx.manager.service.impl
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/7 16:40
 * @Version 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioProperties minioProperties;
    @Override
    public Result fileUpload(MultipartFile file) {
        try{

            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpointUrl())//.endpoint("http://127.0.0.1:9000")
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                    .build();

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());

            // 如果不存在，那么此时就创建一个新的桶
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {  // 如果存在打印信息
                System.out.println("Bucket "+minioProperties.getBucketName()+" already exists.");
            }

            //获取文件名称 使用uuid使文件名称唯一
            //根据当前日期对上传文件进行分组 20240407
            //20240407/01.jpg
            String datadir=DateUtil.format(new Date(),"yyyyMMdd");
            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            String fileName=datadir+"/"+uuid+file.getOriginalFilename();;

            //String fileName=file.getOriginalFilename();
            //FileInputStream fis = new FileInputStream(fileName);
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(file.getInputStream(),file.getSize(), -1)
                    .object(fileName)
                    .build();
            minioClient.putObject(putObjectArgs);

            // 构建fileUrl
            String fileUrl = minioProperties.getEndpointUrl()+"/"+minioProperties.getBucketName()+"/"+fileName;
            return Result.build(fileUrl, ResultCodeEnum.SUCCESS);
        }catch(Exception e){
                e.printStackTrace();
                throw new spzxException(ResultCodeEnum.SYSTEM_ERROR);
            }

        }
}
