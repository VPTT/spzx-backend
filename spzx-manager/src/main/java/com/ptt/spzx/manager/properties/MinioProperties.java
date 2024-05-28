package com.ptt.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: MinioProperties
 * Package: com.ptt.spzx.manager.properties
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/7 17:04
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.minio")
public class MinioProperties {
    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;

}
