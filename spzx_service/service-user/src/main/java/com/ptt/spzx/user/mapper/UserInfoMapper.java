package com.ptt.spzx.user.mapper;

import com.ptt.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserInfoMapper
 * Package: com.ptt.spzx.user.mapper
 * Description:
 *
 * @Author ptt
 * @Create 2024/4/19 14:11
 * @Version 1.0
 */
@Mapper
public interface UserInfoMapper {
    UserInfo selectByUserName(String username);

    void insert(UserInfo userInfo);
}
