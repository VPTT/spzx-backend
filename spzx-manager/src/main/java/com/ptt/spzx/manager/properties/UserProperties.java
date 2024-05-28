package com.ptt.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * ClassName: UserProperties
 * Package: com.ptt.spzx.manager.properties
 * Description:  读取配置文件中的内容
 *
 * @Author ptt
 * @Create 2024/3/29 17:15
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix ="spzx.auth" )
public class UserProperties {
    private List<String> noAuthUrls;
}
