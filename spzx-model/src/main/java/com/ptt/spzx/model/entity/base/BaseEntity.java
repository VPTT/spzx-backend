package com.ptt.spzx.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: BaseEntity
 * Package: com.ptt.spzx.model.entity.base
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/29 10:21
 * @Version 1.0
 */
@Data
public class BaseEntity implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTime;
    private Integer isDeleted;
}
